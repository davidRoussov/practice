package main

import "fmt"

func main() {
	var a = [5]int{1, 2, 3, 4, 5}
	fmt.Println(a)

	a[2] = 999

	fmt.Println(a)

	a = remove(a, 2)

	fmt.Println(a)
}

func remove(s []int, i int) []int {
	s[i] = s[len(s) - 1]
	return s[:len(s) - 1]
}
