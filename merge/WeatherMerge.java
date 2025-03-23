import java.io.*;
import java.util.ArrayList;

class WeatherData{
  String date;
  int temp;

  public WeatherData(String date, int temp){
    this.date = date;
    this.temp = temp;
  }
}

public class WeatherMerge{
  public static void main(String[] args) throws IOException{
    String filePath = "../../daegu_weather_2024.txt";
    ArrayList<WeatherData> dataList = readData(filePath);

    WeatherData[] arr = dataList.toArray(new WeatherData[0]);
    mergeSort(arr, 0, arr.length-1);
    
    saveSortedData(arr);
    printTopThree(arr);
  }

  public static ArrayList<WeatherData> readData(String filePath) throws IOException{
    ArrayList<WeatherData> list = new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
    String line;
    while((line = br.readLine()) != null){
      String[] arr = line.split(" ");
      if(arr.length == 2){
        String date = arr[0];
        int temp = Integer.parseInt(arr[1]);
        list.add(new WeatherData(date, temp));
        }
      }
    }
    return list;
  }

 public static void mergeSort(WeatherData[] dataList, int left, int right){
   if(left < right){
     int mid = (left + right) / 2;

     mergeSort(dataList,left, mid);
     mergeSort(dataList, mid+1, right);

     merge(dataList, left, mid, right);
   }
 }

 public static void merge(WeatherData[] dataList, int left, int mid, int right){
   int n1 = mid - left + 1;
   int n2 = right - mid;
   WeatherData[] L = new WeatherData[n1];
   WeatherData[] R = new WeatherData[n2];

   for(int i = 0; i < n1; i++) L[i] = dataList[left + i];
   for(int j = 0; j < n2; j++) R[j] = dataList[mid + 1 + j];

   int i = 0, j = 0, k = left;
   while(i < n1 && j < n2){
     if(L[i].temp >= R[j].temp){
       dataList[k++] = L[i++];
     }
     else{
       dataList[k++] = R[j++];
     }
   }
   while (i < n1) dataList[k++] = L[i++];
   while (j < n2) dataList[k++] = R[j++];
 }

 public static void saveSortedData(WeatherData[] dataList) throws IOException{
  File file = new File("../sortedWeatherData.txt");

  if(!file.exists()){
    file.createNewFile();
  }

  FileWriter fw = new FileWriter(file);
  BufferedWriter writer = new BufferedWriter(fw);

  for(int i = 0; i < dataList.length; i++){
    writer.write(dataList[i].date + " " + dataList[i].temp);
    writer.newLine();
  }

  writer.close();
 }
 
 public static void printTopThree(WeatherData[] dataList){
   int n = dataList.length;

   System.out.println("가장 더웠던 날");
   for(int i = 0; i < 3; i++){
     System.out.println(dataList[i].date + " " + dataList[i].temp);
   }
 }
}
