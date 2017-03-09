import org.apache.spark.{SparkContext, SparkConf}

object SparkWordCount {

  def main(args: Array[String]) {

    System.setProperty("hadoop.home.dir","C:\\Users\\prake\\Documents\\winutils");

    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc=new SparkContext(sparkConf)

    val input=sc.textFile("input")
    //Transformation 1:map and flatmap  operations
    val oper1=input.flatMap(line=>{line.split(" ")}).map(word=>(word,1)).cache()

    //Action1: collects all items
    oper1.collect()

    //Action 2: count
    oper1.count()

    //Action 3: Display Items as required
    oper1.take(4)

    //Transformation 2:Reducebykey transformation
    val oper2=oper1.reduceByKey(_+_)

    //Transformation 3: sortby transformation
    val result = oper2.sortBy(_._2, false)

    //Action 3:saveastextfile
    result.saveAsTextFile("output2")

    //Action 4: To retrieve first element from RDD
    val y=result.first()
    print("Display the word which has been repeated most in the news Article:"+y)
    val o=result.collect()

    var s:String="Words:Count \n"
    o.foreach{case(word,count)=>{

      s+=word+" : "+count+"\n"

    }}

  }

}