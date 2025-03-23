import java.io.*;
import java.util.ArrayList;

class WeatherData{ //날씨데이터를 저장할 클래스
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
    ArrayList<WeatherData> dataList = readData(filePath); //날씨 데이터를 읽어올 함수

    WeatherData[] arr = dataList.toArray(new WeatherData[0]); //리스트를 배열로 변환
    mergeSort(arr, 0, arr.length-1); //mergesort 실행함수
    
    saveSortedData(arr); //정렬된 데이터 파일로 저장
    printTopThree(arr); //데이터 중 가장 더웠던 3일 출력
  }

  //text파일에서 데이터를 읽어오는 함수
  public static ArrayList<WeatherData> readData(String filePath) throws IOException{
    ArrayList<WeatherData> list = new ArrayList<>();
    try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
    String line;
    while((line = br.readLine()) != null){
      String[] arr = line.split(" "); //띄어쓰기를 기준으로 날짜와 온도로 나눔
      if(arr.length == 2){
        String date = arr[0];
        int temp = Integer.parseInt(arr[1]); //temp는 비교를 위해 int로 변환
        list.add(new WeatherData(date, temp)); //날짜와 온도를 list에 추가
        }
      }
    }
    return list;
  }

  //배열분할 
  public static void mergeSort(WeatherData[] dataList, int left, int right){ 
    if(left < right){
      int mid = (left + right) / 2; //배열을 나눌 기준

      mergeSort(dataList,left, mid); //왼쪽을 반복해서 나눔
      mergeSort(dataList, mid+1, right); //오른쪽을 반복해서 나눔

      merge(dataList, left, mid, right);
    }
  }

  public static void merge(WeatherData[] dataList, int left, int mid, int right){ //병합함수
    // 왼쪽, 오른쪽 배열 크기
    int n1 = mid - left + 1;
    int n2 = right - mid;
    //임시 배열
    WeatherData[] L = new WeatherData[n1];
    WeatherData[] R = new WeatherData[n2];

    //왼쪽, 오른쪽 임시배열에 값을 복사
    for(int i = 0; i < n1; i++) L[i] = dataList[left + i];
    for(int j = 0; j < n2; j++) R[j] = dataList[mid + 1 + j];

    //왼쪽, 오른쪽 배열을 비교하면서 내림차순으로 병합
    int i = 0, j = 0, k = left;
    while(i < n1 && j < n2){
      if(L[i].temp >= R[j].temp){
        dataList[k++] = L[i++];
      }
      else{
        dataList[k++] = R[j++];
      }
    }

    //남은 요소 복사
    while (i < n1) dataList[k++] = L[i++];
    while (j < n2) dataList[k++] = R[j++];
  }


  //정렬된 배열 저장
  public static void saveSortedData(WeatherData[] dataList) throws IOException{
    File file = new File("../sortedWeatherData.txt");

    if(!file.exists()){ //파일생성여부확인
      file.createNewFile();
    }

    FileWriter fw = new FileWriter(file);
    BufferedWriter writer = new BufferedWriter(fw);

    for(int i = 0; i < dataList.length; i++){ //순서대로 날짜 온도 형식으로 저장
      writer.write(dataList[i].date + " " + dataList[i].temp);
      writer.newLine();
    }

    writer.close();
  }
  public static void printTopThree(WeatherData[] dataList){ //가장 더웠던 3일 출력함수
    int n = dataList.length;

    System.out.println("가장 더웠던 날");
    for(int i = 0; i < 3; i++){ //상위 3개 테이터 출력
      System.out.println(dataList[i].date + " " + dataList[i].temp);
    }
  }
}
