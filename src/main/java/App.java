public class App {
    public static void main(String[] args) {
//        String csvFile = "../aapl_us_d.csv";
        String csvFile = "D:/Download/aapl_us_d.csv";

        System.out.println("Hello Exiles!");

        DataLoader dataLoader = new DataLoader(csvFile);

        dataLoader.readData();
    }
}
