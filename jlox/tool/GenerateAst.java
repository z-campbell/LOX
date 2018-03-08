package com.craftinginterpreters.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        if (args.length !=1) {
            System.err.println("Usage: generate ast <output directory");
            System.exit(1);
        }
        String outputDir = args[0];
        defineAst(outputDir, "Expr", Arrays.asList(
                "Binary : Expr left, Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal : Object value",
                " Unary : Token operator, Expr right"
        ));
    }
    private static void defineAst(
            String outputDir, String basename, List<String> types)
        throws IOException {
        String path = outputDir + "/" + basename + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writer.println("package com.craftinginterpreters.lox;");
        writer.println("");
        writer.println("import java.util.List;");
        writer.println("");
        writer.println("abstract class " + baseName + " {");
        // The AST Classes
        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(write, baseName, className, fields);
        }

        writer.println("}");
        writer.close();

    }

}