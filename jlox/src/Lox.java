package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {

    static boolean hadError = false;

    public static void main (String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    /*/
            When the Lox interpreter is run from the command line with a file arg,
            it will read the file into memory and execute the file.
     /*/
    private static void runFile ( String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if  (hadError) System.exit(65);
    }

    /*
        When the Lox interpreter is executed from the command line with no args,
        an interactive interpreter is launched. This acts as a simple REPL and
        takes no active arguments.

     */
    private static void runPrompt () throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            run(reader.readLine());
            hadError = false;
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        //For now just print tokens
        for (Token token : tokens) {
            System.out.println(token);
        }

    }

    static void error(int line, String message) {
        report(line, "", message);

    }

    private static void report(int line, String where, String message){
        System.err.println(
           "[line " + line + "] Error" + where + ": " + message
        );
    }
}

