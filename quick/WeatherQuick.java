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

class PivotCount{ //pivot 횟수
  int count = 0;
}

public class WeatherQuick{
  public static void main(String[] args) throws IOException{
    String filePath = "../daegu_weather_2024.txt";
    ArrayList<WeatherData> dataList = readData(filePath); //날씨 데이터를 읽어올 함수

    WeatherData[] arr = dataList.toArray(new WeatherData[0]); //리스트를 배열로 변환
    PivotCount pivotCount = new PivotCount();
    quickSort3Way(arr, 0, arr.length-1, pivotCount); //quickSort 실행함수
    
    System.out.println("Pivot 선택 횟수: " + pivotCount.count);
    saveSortedData(arr); //정렬된 데이터 파일로 저장
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

  //3-way QuickSort 정렬 함수
  public static void quickSort3Way(WeatherData[] dataList, int left, int right, PivotCount count){
    if(left >= right) return;// 정렬 필요없는 경우 종료

      //pivot 랜덤 선택
      count.count++;
      int pivotIndex = left + (int)(Math.random() * (right - left + 1));
      WeatherData pivot = dataList[pivotIndex];
      swap(dataList, left, pivotIndex);//pivot을 가장 왼쪽으로 이동

      //3-Way를 위한 인덱스 설정
      int leftSection = left;// pivot보다 작은 값의 끝
      int i = left +1;// 현재 비교
      int rightSection = right;//pivot보다 큰 값을 시작

      while(i <= rightSection){// pivot 비교
        if (dataList[i].temp < pivot.temp){
          swap(dataList, leftSection++, i++);
        }
        else if(dataList[i].temp > pivot.temp){
          swap(dataList, i, rightSection--);
        }
        else{//pivot과 값이 같으면 다음 값을 비교
          i++;
        }
      }
      
      //pivot보다 작은 구간, 큰 구간 반복 정렬
      quickSort3Way(dataList, left, leftSection-1, count);
      quickSort3Way(dataList, rightSection+1, right, count);
  }

  //배열의 두 요소 위치 변경
  public static void swap(WeatherData[] dataList, int i, int j){
    WeatherData tmp = dataList[i];
    dataList[i] = dataList[j];
    dataList[j] = tmp;
  }

  //정렬된 배열 저장
  public static void saveSortedData(WeatherData[] dataList) throws IOException{
    File file = new File("QuickSortedWeatherData.txt");

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
}
