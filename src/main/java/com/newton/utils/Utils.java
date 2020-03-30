package com.newton.utils;

import com.newton.Application;
import com.newton.model.Slot;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Utils {

  private static String USAGE = null;

  public static void printUsage() {
    if (USAGE == null) {
      ClassLoader classLoader = Application.class.getClassLoader();
      InputStream resourceAsStream = classLoader.getResourceAsStream("usage.txt");

      try {
        USAGE = new String(resourceAsStream.readAllBytes(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.out.println(String.format("Exception: %s", e.getMessage()));
        System.exit(0);
      }
    }
    System.out.println(USAGE);
  }

  public static void printStatus(List<Slot> status) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Slot No\t| Registration No \t| Colour\n");
    stringBuilder.append("---------------------------------------").append("\n");
    status.forEach(
        slot ->
            stringBuilder
                .append(
                    String.format(
                        "  %d\t| %s\t\t| %s",
                        slot.getId(), slot.getVehicle().getRegNo(), slot.getVehicle().getColour()))
                .append("\n"));
    System.out.println(stringBuilder.toString());
  }
}
