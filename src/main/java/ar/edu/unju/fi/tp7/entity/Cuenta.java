package ar.edu.unju.fi.tp7.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table( name = "CUENTAS")
public class Cuenta {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cue_id")
  private Long id;

  @Column(name = "cue_saldo")
  private double saldo;

  @Column(name = "cue_fechaCreacion")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaCreacion;

  @Column(name = "cue_estado")
  private String estado;

  @OneToOne(mappedBy = "cuenta", fetch = FetchType.LAZY)
  private Cliente cliente;

  // Constructor Vacio
  public Cuenta() {
  }

  // Constructor Parametrizado
  public Cuenta(double saldo, LocalDate fechaCreacion, String estado, Cliente cliente) {
    this.saldo = saldo;
    this.fechaCreacion = fechaCreacion;
    this.estado = estado;
    this.cliente = cliente;
  }

  
  // Getter and Setter
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDate fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  // to String
  @Override
  public String toString() {
    return "Cuenta [cliente=" + cliente + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", id=" + id
        + ", saldo=" + saldo + "]";
  }



}
