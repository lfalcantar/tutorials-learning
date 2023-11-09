package main

import (
	"bytes"
	"encoding/json"
	"io"
	"net/http"
	"net/http/httptest"
	"reflect"
	"testing"

	"github.com/gorilla/mux"
)

func TestUsersHandler_Get(t *testing.T) {
	req, err := http.NewRequest("GET", "/users", nil)
	if err != nil {
		t.Fatal(err)
	}

	rr := httptest.NewRecorder()
	handler := http.HandlerFunc(usersHandler)

	handler.ServeHTTP(rr, req)

	if status := rr.Code; status != http.StatusOK {
		t.Errorf("handler returned wrong status code: got %v want %v", status, http.StatusOK)
	}

	var actual []User
	err = json.Unmarshal([]byte(rr.Body.String()), &actual)
	if err != nil {
		t.Fatal(err)
	}

	var expected []User
	err = json.Unmarshal([]byte(`[{"name":"John Smith","age":30},{"name":"Jane Doe","age":20}]`), &expected)
	if err != nil {
		t.Fatal(err)
	}

	if !reflect.DeepEqual(actual, expected) {
		t.Errorf("handler returned unexpected body: got %v want %v", actual, expected)
	}
}

func TestUsersCreateHandler(t *testing.T) {
	r := mux.NewRouter()
	r.HandleFunc("/users", usersCreateHandler).Methods("POST")

	// Create a new user to create.
	newUser := User{
		Name: "Carol",
		Age:  25,
	}

	// Marshal the new user to JSON.
	newUserJson, err := json.Marshal(newUser)
	if err != nil {
		t.Errorf("Failed to marshal new user to JSON: %v", err)
	}

	// Create a new HTTP request to create the new user.
	req, err := http.NewRequest("POST", "/users", bytes.NewReader(newUserJson))
	if err != nil {
		t.Errorf("Failed to create new HTTP request: %v", err)
	}

	// Create a response recorder to capture the response.
	rec := httptest.NewRecorder()

	// Serve the request.
	r.ServeHTTP(rec, req)

	// Check the status code.
	if rec.Code != http.StatusOK {
		t.Errorf("Expected status code %d, but got %d", http.StatusCreated, rec.Code)
	}

	// Decode the response body.
	responseBody := rec.Body
	responseBodyBytes, err := io.ReadAll(responseBody)
	if err != nil {
		t.Errorf("Failed to read response body: %v", err)
	}

	var allUsers []User
	err = json.Unmarshal(responseBodyBytes, &allUsers)
	if err != nil {
		t.Errorf("Failed to unmarshal response body to JSON: %v", err)
	}

	// Check that the new user is included in the response.
	newUserFound := false
	for _, user := range allUsers {
		if user.Name == "Carol" && user.Age == 25 {
			newUserFound = true
			break
		}
	}

	if !newUserFound {
		t.Errorf("New user not found in response")
	}
}
