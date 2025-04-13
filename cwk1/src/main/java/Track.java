import java.io.IOException;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * 
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author YOUR NAME
 */
public class Track {
  // TODO: Create a stub for the constructor
  private final List<Point> points = new ArrayList<>(); // 添加字段声明

  public Track(String filename) throws IOException {
    readFile(filename);
  }

  // TODO: Create a stub for readFile()
  public void readFile(String filename) throws IOException {
    points.clear();
    
    try (Scanner scanner = new Scanner(Path.of(filename))) {
      // 跳过CSV头
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }

      int lineNumber = 1;
      while (scanner.hasNextLine()) {
        lineNumber++;
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) continue;

        String[] columns = line.split(",");
        if (columns.length < 4) {
          throw new GPSException("Invalid data at line " + lineNumber + ": need 4 columns");
        }

        try {
          ZonedDateTime time = ZonedDateTime.parse(columns[0].trim());
          double lon = Double.parseDouble(columns[1].trim());
          double lat = Double.parseDouble(columns[2].trim());
          double elev = Double.parseDouble(columns[3].trim());
          points.add(new Point(time, lon, lat, elev));
        } catch (Exception e) {
          throw new GPSException("Error parsing line " + lineNumber + ": " + e.getMessage());
        }
      }
    }
  }

  // TODO: Create a stub for add()
  public void add(Point point) {
    points.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index) {
    if (index < 0 || index >= points.size()) {
      throw new GPSException("Invalid index: " + index);
    }
    return points.get(index);
  }

  // TODO: Create a stub for size()
  public int size() {
    return points.size();
  }

  // TODO: Create a stub for lowestPoint()
   public Point lowestPoint() throws GPSException {
        if (points.isEmpty()) {
            throw new GPSException("Track has no points");
        }
        Point lowest = points.get(0);
        for (Point p : points) {
            if (p.getElevation() < lowest.getElevation()) {
                lowest = p;
            }
        }
        return lowest;
    }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
