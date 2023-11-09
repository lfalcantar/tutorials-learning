package main

import (
	"encoding/json"
	"io"
	"net/http"

	"github.com/gorilla/mux"
)

type User struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

var users = []User{
	{
		Name: "John Smith",
		Age:  30,
	},
	{
		Name: "Jane Doe",
		Age:  20,
	},
}

func usersHandler(w http.ResponseWriter, r *http.Request) {
	// w is the response
	w.Header().Set("Content-Type", "application/json")

	encoder := json.NewEncoder(w)
	encoder.Encode(users)
}

func usersCreateHandler(w http.ResponseWriter, r *http.Request) {
	body, err := io.ReadAll(r.Body)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	var user User
	err = json.Unmarshal(body, &user)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	users = append(users, user)

	w.Header().Set("Content-Type", "application/json")
	encoder := json.NewEncoder(w)
	encoder.Encode(users)
}

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/users", usersHandler).Methods("GET")
	r.HandleFunc("/users", usersCreateHandler).Methods("POST")
	http.ListenAndServe(":8080", r)
}
