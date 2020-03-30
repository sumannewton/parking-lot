package com.newton;

import com.newton.dao.InMemoryDB;
import com.newton.dao.InMemorySlotDao;
import com.newton.dao.InMemoryVehicleDao;
import com.newton.exception.ParkingException;
import com.newton.instruction.ParkingInstruction;
import com.newton.service.ParkingManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Application {

  public static void main(String[] args) {
    printUsgae();

    // Initialize all objects
    InMemoryDB db = new InMemoryDB();
    ParkingManager parkingManager =
        new ParkingManager(new InMemoryVehicleDao(db), new InMemorySlotDao(db));
    ParkingInstruction parkingInstruction = new ParkingInstruction(parkingManager);

    if (args.length == 0) {
      startInteractiveCommandPrompt(parkingInstruction);
    } else {
      startReadingCommandsFromFile(parkingInstruction);
    }
  }

  private static void startReadingCommandsFromFile(ParkingInstruction parkingInstruction) {}

  private static void startInteractiveCommandPrompt(ParkingInstruction parkingInstruction) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line;
    while (true) {
      try {
        line = reader.readLine();
        if (line.equalsIgnoreCase("exit")) {
          System.out.println("Shutting down app....ex");
          System.exit(0);
        }
        String[] inputs = line.split(" ");
        try {
          parkingInstruction.execute(inputs);
        } catch (ParkingException e) {
          System.out.println(e.getMessage());
        }
      } catch (IOException e) {
        printUsgae();
      }
    }

  }

  private static void printUsgae() {
    ClassLoader classLoader = Application.class.getClassLoader();
    InputStream resourceAsStream = classLoader.getResourceAsStream("usage.txt");


    try {
      System.out.println(new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8));
//      System.out.println("Full Path: " + file.getCanonicalPath());
//      printFile(file);
    } catch (IOException e) {
      System.out.println(String.format("Exception: %s", e.getMessage()));
    }
  }

  private static void printFile(File file) throws IOException {

    if (file == null) return;

    try (FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader)) {

      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    }
  }
}
