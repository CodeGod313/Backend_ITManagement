package by.vita02.backend.order;

import by.vita02.backend.result.ITProject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean isAccepted;
  private boolean isPayed;

  @Column(name = "dateOfCreation")
  private Date date;

  private int cost;
  private int count;

  @OneToOne(cascade = CascadeType.ALL)
  private ITProject itProject;

  private String companyName;

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Order(boolean isAccepted, int cost, int count, String companyName) {
    this.isAccepted = isAccepted;
    this.cost = cost;
    this.count = count;
    this.companyName = companyName;
    date = new Date();
    // itProject = new ITProject(ProjectType.BUSINESS_CARD_SITE, 15, "suka");
  }

  public boolean isPayed() {
    return isPayed;
  }

  public void setPayed(boolean payed) {
    isPayed = payed;
  }

  public Order() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isAccepted() {
    return isAccepted;
  }

  public void setAccepted(boolean accepted) {
    isAccepted = accepted;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public ITProject getItProject() {
    return itProject;
  }

  public void setItProject(ITProject itProject) {
    this.itProject = itProject;
  }
}
