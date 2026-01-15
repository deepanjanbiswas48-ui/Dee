package main

import (
	"fmt"
	"net/http"
)

// এই সার্ভারটি টারমাক্সে অ্যাড ফিল্টার লিস্ট এবং রুলস ম্যানেজ করবে
func main() {
	http.HandleFunc("/status", func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(w, "Beast Engine V.6.6.6.6 is Running")
	})

	fmt.Println("Server starting on port 8080...")
	http.ListenAndServe(":8080", nil)
}
