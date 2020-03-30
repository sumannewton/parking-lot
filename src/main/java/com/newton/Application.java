package com.newton;

import com.newton.dao.InMemoryDB;
import com.newton.dao.InMemorySlotDao;
import com.newton.dao.InMemoryVehicleDao;
import com.newton.exception.ParkingException;
import com.newton.handler.ParkingInstructionHandler;
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
    ParkingInstructionHandler parkingInstructionHandler =
        new ParkingInstructionHandler(parkingManager);

    if (args.length == 0) {
      startInteractiveCommandPrompt(parkingInstructionHandler);
    } else {
      startReadingCommandsFromFile(parkingInstructionHandler, args[0]);
    }
  }

  private static void startReadingCommandsFromFile(
      ParkingInstructionHandler parkingInstructionHandler, String file) {
    try {
      FileReader fileReader = new FileReader(new File(file));
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      readAndExecute(parkingInstructionHandler, bufferedReader, false);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void startInteractiveCommandPrompt(
      ParkingInstructionHandler parkingInstructionHandler) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    readAndExecute(parkingInstructionHandler, reader, true);
  }

  private static void readAndExecute(
      ParkingInstructionHandler parkingInstructionHandler, BufferedReader reader, boolean cmd) {
    String line;
    while (true) {
      if (cmd) System.out.print("cmdline> ");
      try {
        line = reader.readLine();
        String[] inputs = new String[] {"exit"};
        if (line != null) {
          inputs = line.split(" ");
        }
        try {
          parkingInstructionHandler.execute(inputs);
        } catch (ParkingException e) {
          System.out.println(e.getMessage());
        }
      } catch (IOException e) {
        Utils.printUsage();
      }
    }
  }
}
