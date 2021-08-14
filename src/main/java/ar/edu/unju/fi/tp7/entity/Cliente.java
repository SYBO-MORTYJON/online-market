/**
 * 
 */
package ar.edu.unju.fi.tp7.entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * It uses MySQL
 * 
 * @author Team Fernet
 *
 */
@Entity
@Component
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private long id;

	@Column(name = "cli_tipoDocumento", length = 32, nullable = false)
	private String tipoDocumento;

	@Column(name = "cli_nroDocumento", length = 8, nullable = false)
	private int nroDocumento;

	@Column(name = "cli_nombreApellido", length = 64, nullable = false)
	private String nombreApellido;

	@Column(name = "cli_email", length = 128, nullable = false)
	private String email;

	@Column(name = "cli_password", length = 64, nullable = false)
	private String password;

	@Column(name = "cli_fechaNacimiento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;

	@Column(name = "cli_edad")
	private int edad;

	@Column(name = "cli_codigoAreaTelefono", nullable = false)
	private String codigoAreaTelefono;

	@Column(name = "cli_nroTelefono", nullable = false)
	private int nroTelefono;

	@Column(name = "cli_fechaUltimaCompra")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cue_id")
	private Cuenta cuenta;

	public Cliente() {
	}

	/**
	 * @param tipoDocumento
	 * @param nroDocumento
	 * @param nombreApellido
	 * @param email
	 * @param password
	 * @param fechaNacimiento
	 * @param codigoAreaTelefono
	 * @param nroTelefono
	 * @param fechaUltimaCompra
	 */
	public Cliente(String tipoDocumento, int nroDocumento, String nombreApellido, String email, String password,
			LocalDate fechaNacimiento, String codigoAreaTelefono, int nroTelefono, LocalDate fechaUltimaCompra) {
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.codigoAreaTelefono = codigoAreaTelefono;
		this.nroTelefono = nroTelefono;
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	/**
	 * 
	 * @return Tiempo transcurrido entre última compra y la fecha actual.
	 */
	public String getTiempoTranscurridoUltimaCompra() {
		String tiempoTranscurrido = "0";
		if (fechaUltimaCompra != null) {
			Period periodo = Period.between(fechaUltimaCompra, LocalDate.now());
			tiempoTranscurrido = String.valueOf(periodo.getYears()) + " años-" + String.valueOf(periodo.getMonths())
					+ " meses-" + String.valueOf(periodo.getDays() + " días");
		}
		return tiempoTranscurrido;
	}

	/**
	 * 
	 * @return Dias transcurridos desde la fecha de nacimiento hasta la fecha
	 *         actual.
	 */
	public long getTiempoTranscurridoEnDiasFechaNacimiento() {
		long diasTranscurridos = 0;
		if (fechaNacimiento != null) {
			diasTranscurridos = ChronoUnit.DAYS.between(fechaNacimiento, LocalDate.now());
		}
		return diasTranscurridos;
	}

	/**
	 * 
	 * @return Tiempo que falta para el próximo cumpleaños.
	 */
	public String getTiempoProximoCumple() {
		String proximoCumple = "";
		LocalDate hoy = LocalDate.now();

		LocalDate siguienteCumple = this.fechaNacimiento.withYear(hoy.getYear());

		if (siguienteCumple.isBefore(hoy) || siguienteCumple.isEqual(hoy)) {
			siguienteCumple = siguienteCumple.plusYears(1);
		}

		Period periodo = Period.between(hoy, siguienteCumple);

		proximoCumple = String.valueOf(periodo.getDays()) + "-" + String.valueOf(periodo.getMonths()) + "-"
				+ String.valueOf(periodo.getYears());

		LocalDateTime CumpleAhora = LocalDateTime.now();

		LocalDateTime proximoCumpleEnHora = LocalDateTime.of(siguienteCumple.getYear(), siguienteCumple.getMonth(),
				siguienteCumple.getDayOfMonth(), 0, 0, 0);

		Duration duracion = Duration.between(CumpleAhora, proximoCumpleEnHora);

		proximoCumple = proximoCumple + " " + duracion.toHoursPart() + ":" + duracion.toMinutesPart() + ":"
				+ duracion.toSecondsPart();

		return proximoCumple;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the nroDocumento
	 */
	public int getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * @param nroDocumento the nroDocumento to set
	 */
	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * @return the nombreApellido
	 */
	public String getNombreApellido() {
		return nombreApellido;
	}

	/**
	 * @param nombreApellido the nombreApellido to set
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the edad según el cálculo entre fechaDeNacimiento y la fecha actual
	 */
	public int getEdad() {
		int edad = 0;
		if (fechaNacimiento != null) {
			edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
		}
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * @return the codigoAreaTelefono
	 */
	public String getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	/**
	 * @param codigoAreaTelefono the codigoAreaTelefono to set
	 */
	public void setCodigoAreaTelefono(String codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	/**
	 * @return the nroTelefono
	 */
	public int getNroTelefono() {
		return nroTelefono;
	}

	/**
	 * @param nroTelefono the nroTelefono to set
	 */
	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	/**
	 * @return the fechaUltimaCompra
	 */
	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	/**
	 * @param fechaUltimaCompra the fechaUltimaCompra to set
	 */
	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento
				+ ", nombreApellido=" + nombreApellido + ", email=" + email + ", password=" + password
				+ ", fechaNacimiento=" + fechaNacimiento + ", edad=" + edad + ", codigoAreaTelefono="
				+ codigoAreaTelefono + ", nroTelefono=" + nroTelefono + ", fechaUltimaCompra=" + fechaUltimaCompra
				+", cuenta=" + cuenta + "]";
	}

}