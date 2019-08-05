package com.waytoodanny;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Path path1 = Paths.get("src/main");
        Path path2 = Paths.get("resources/t.txt");
        Path path3 = path1.resolve(path2);

        System.out.println(Files.newBufferedReader(path3).readLine());
    }
}
