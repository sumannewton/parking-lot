package com.newton;

import com.newton.dao.InMemoryDB;
import com.newton.dao.InMemorySlotDao;
import com.newton.dao.InMemoryVehicleDao;
import com.newton.exception.ParkingException;
import com.newton.instruction.ParkingInstruction;
import com.newton.service.ParkingManager;
import com.newton.utils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

  public static void main(String[] args) {
    System.out.println("Parking lot service is starting....");
    Utils.printUsage();

    // Initialize all objects
    InMemoryDB db = new InMemoryDB();
    ParkingManager parkingManager =
        new ParkingManager(new InMemoryVehicleDao(db), new InMemorySlotDao(db));
    ParkingInstruction parkingInstruction = new ParkingInstruction(parkingManager);

    if (args.length == 0) {
      startInteractiveCommandPrompt(parkingInstruction);
    } else {
      startReadingCommandsFromFile(parkingInstruction, args[0]);
    }
  }

  private static void startReadingCommandsFromFile(
      ParkingInstruction parkingInstruction, String file) {
    try {
      FileReader fileReader = new FileReader(new File(file));
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      readAndExecute(parkingInstruction, bufferedReader);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void startInteractiveCommandPrompt(ParkingInstruction parkingInstruction) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    readAndExecute(parkingInstruction, reader);
  }

  private static void readAndExecute(ParkingInstruction parkingInstruction, BufferedReader reader) {
    while (true) {
      String line;
      try {
        line = reader.readLine();
        if (line == null || line.equalsIgnoreCase("exit")) {
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
        Utils.printUsage();
      }
    }
  }
}
