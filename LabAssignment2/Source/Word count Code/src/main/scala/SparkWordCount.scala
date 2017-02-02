

import org.apache.spark.{SparkContext, SparkConf}
object SparkWordCount {

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","C:\\Users\\prake\\Documents\\winutils");

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc=new SparkContext(sparkConf)

    val input=sc.textFile("input")

    val wc=input.flatMap(line=>{line.split(" ")}).map(word=>(word,1)).cache()

    //
    wc.collect()


    val output=wc.reduceByKey(_+_)

    output.saveAsTextFile("output1")

    val o=output.collect()

    var s:String="Words:Count \n"
    o.foreach{case(word,count)=>{

      s+=word+" : "+count+"\n"

    }}

  }

}

object SparkActions {

  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "C:\\Users\\prake\\Documents\\winutils");

    val sparkConf = new SparkConf().setAppName("SparkActions").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val nums = sc.parallelize(Array(1, 2, 3))
    // Retrieve RDD contents as a local collection
    nums.collect() // => [1, 2, 3]
    //Return first K elements
    nums.take(2) // => [1, 2]
    //Count number of elements
    nums.count() // => 3
    //Merge elements with an associative function
    nums.reduce((x, y) => (x + y)) // => 6
    //Write elements to a text file
    nums.saveAsTextFile("file.txt")

  }
}

object SparkTransformation {
  def main(args: Array[String]): Unit = {


    System.setProperty("hadoop.home.dir", "C:\\Users\\prake\\Documents\\winutils");

    val sparkConf = new SparkConf().setAppName("SparkTransformation").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val nums = sc.parallelize(Array(1, 2, 3))
    // Pass each element through a function
    val squares = nums.map(x => (x * x)) // => {1, 4, 9}

    // Keep elements passing a predicate
    val even = squares.filter(x => x % 2 == 0) // => {4}


    // Map each element to zero or more others['
    val result = nums.flatMap(x => Array.range(0, x)) //=> {0, 0, 1, 0, 1, 2}

    result.foreach(println(_))

  }
}
