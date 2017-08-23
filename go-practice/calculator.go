package main

import (
	"bufio"
	"os"
	"fmt"
)

func main() {
	reader := bufio.NewReader(os.Stdin)
	fmt.Print("Enter input: ")
	text, _ := reader.ReadString('\n')
	parse(text)
}

func parse(text string) {
	text = text[:len(text) - 2] // remove newlines
	var tokens = tokenize(text)
	
	fmt.Printf("%v", tokens)
}

// current tokens that can are parsed
// + - * / ( ) integers
func tokenize(text string) []string {
	var tokens []string

	var position int = 0
	for position < len(text) {

		var character = string(text[position])
		switch character {
			case "+":
				fmt.Println("PLUS")
				tokens = append(tokens, character)
				position++
			case "-":
				fmt.Println("MINUS")
				tokens = append(tokens, character)
				position++
			case "*":
				fmt.Println("TIMES")
				tokens = append(tokens, character)
				position++
			case "/":
				fmt.Println("DIVIDE")
				tokens = append(tokens, character)
				position++
			default:
				fmt.Println("INVALID INPUT")
				position++
		}
	}

	return tokens
}