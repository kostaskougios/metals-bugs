package org.easygame.view

trait ModelIdentifiable:
  def id: String
  def parents: Seq[String]             = Nil
  def children: Seq[ModelIdentifiable] = Nil
