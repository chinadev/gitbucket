package model

import scala.slick.driver.H2Driver.simple._

object Milestones extends Table[Milestone]("MILESTONE") {
  def userName = column[String]("USER_NAME", O PrimaryKey)
  def repositoryName = column[String]("REPOSITORY_NAME", O PrimaryKey)
  def milestoneId = column[Int]("MILESTONE_ID", O PrimaryKey)
  def title = column[String]("TITLE")
  def description = column[String]("DESCRIPTION")
  def dueDate = column[java.sql.Date]("DUE_DATE") // TODO convert java.util.Date later
  def closedDate = column[java.sql.Timestamp]("CLOSED_DATE")

  def ins = userName ~ repositoryName ~ title ~ description.? ~ dueDate.? ~ closedDate.?
  def * = userName ~ repositoryName ~ milestoneId ~ title ~ description.? ~ dueDate.? ~ closedDate.? <> (Milestone, Milestone.unapply _)
}

case class Milestone(
  userName: String,
  repositoryName: String,
  milestoneId: Int,
  title: String,
  description: Option[String],
  dueDate: Option[java.sql.Date],
  closedDate: Option[java.sql.Timestamp])