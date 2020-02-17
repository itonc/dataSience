package bigdata
import scala.collection.Map

import scala.collection.mutable.ArrayBuffer
object Test {

  def main(args: Array[String]): Unit = {
    val t = new Trie()

    t.insert("新洲花园")
    t.insert("新洲大厦A栋")


    val invertIndex = InvertedIndex()

    invertIndex.insert("光明城",1)
    invertIndex.insert("城市花园",2)
    invertIndex.insert("远大花园大屋顶",3)
    invertIndex.insert("光明新村",4)

    println(invertIndex.search("南山远大花园2号楼")(helper))

      def helper(index:Map[Char,ArrayBuffer[(Int,Int,Long)]]):(Long,Double)={

        println(index)

        (1,1l)
      }

  }

}
