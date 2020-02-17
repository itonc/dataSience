package bigdata

import scala.collection.mutable
import scala.collection.Map
import scala.collection.mutable.ArrayBuffer

case class InvertedIndex (){

   /*用一个可变的字典存储字符及其对应的文档编号和位置信息
   其中key是 字符
    value为一个 存储三元组 (位置、长度，编号）的数组
    */
   val indexTree = mutable.Map[Char,ArrayBuffer[(Int,Int,Long)]]()

  /**
    * 插值的方法，思想就是通过判断key在Map中是否存在而不断更新Map
    * @param str
    * @param id
    */
  def insert(str:String,id:Long):Unit= {
    val len = str.length
    val zipStr = str.zipWithIndex

    for (zs <- zipStr) {
      val ch = zs._1
      val position = zs._2
      indexTree.get(ch) match {
          //Map中不存在这个key，则加一个新数组，数组有一个元素（位置,长度,文档ID）
        case None => indexTree(ch) = new ArrayBuffer[(Int, Int, Long)]() :+ (position, len, id)
          //Map中存在这个Key，则在数组中增加元素（位置，长度,id）
        case Some(x) => indexTree(ch) = x :+ (position, len, id)
      }
    }
  }

  /*
  搜索的方法，找出Map中所有str包含字符对应的value,中间加了一个f 方法，可以通过传入不同的f函数实现不同业务逻辑
   */
  def search(txt:String)(f:Map[Char,ArrayBuffer[(Int,Int,Long)]] => (Long,Double)):(Long,Double)={
    val r = indexTree.filterKeys(r => txt.contains(r))
    f(r)
  }

  override def toString: String ={
    indexTree.toList.toString()
  }
}
