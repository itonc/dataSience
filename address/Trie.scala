package bigdata

import scala.annotation.tailrec
import scala.collection.mutable


case class Trie() {

  val root = TrieNode(key = '@')

  def insert(str: String): Unit = {
    var ws = root
    for (c <- str) {
      val cn = TrieNode(c)
      val op = ws.childNodes.get(c)
      op match {
        case Some(x) => ws = x
        case _ => {
          ws = ws.addChild(cn)
        }
      }
    }
    ws.isEnd = true
  }



  def startWith(str:String):Boolean={

    @tailrec
    def loop(start:TrieNode,str:String,acc:Boolean):Boolean={
      if(str.isEmpty || !acc) acc
      else
        start.childNodes.get(str.head) match{
          case None => false
          case Some(x) => loop(x,str.tail,acc)
        }
    }

    loop(this.root,str,true)
  }




}

case class  TrieNode(key:Char) {
  var isEnd = false
  val childNodes: mutable.Map[Char, TrieNode] = mutable.Map[Char, TrieNode]()
  def addChild(that: TrieNode): TrieNode = {
    this.childNodes(that.key) = that
    that
  }
}