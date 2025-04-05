package com.secondTask;

import java.util.*;

public class HospitalManagementSystem {
	static Scanner scanner = new Scanner(System.in);
	static List<Patient> patients = new ArrayList<>();
	static List<Appointment> appointments = new ArrayList<>();
	static List<Staff> staffList = new ArrayList<>();

	public static void main(String[] args) {
		while (true) {
			System.out.println("\n Hospital Management System ");
			System.out.println("1. Register Patient");
			System.out.println("2. Schedule Appointment");
			System.out.println("3. Add Staff Member");
			System.out.println("4. View Patients");
			System.out.println("5. View Appointments");
			System.out.println("6. View Staff");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1 -> registerPatient();
			case 2 -> scheduleAppointment();
			case 3 -> addStaff();
			case 4 -> viewPatients();
			case 5 -> viewAppointments();
			case 6 -> viewStaff();
			case 7 -> {
				System.out.println("Exiting system. Thank you!");
				return;
			}
			default -> System.out.println("Unrecognized input. Please choose a number from the menu.");
			}
		}
	}

	static void registerPatient() {
		System.out.print("Enter patient name: ");
		String name = scanner.nextLine();

		System.out.print("Enter age: ");
		int age = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter gender: ");
		String gender = scanner.nextLine();

		System.out.print("Enter contact number: ");
		String contact = scanner.nextLine();
		String[] issues = { "Fever", "Cold", "Headache", "Stomach Pain", "Kidney Stone", "Diabetes", "Cancer",
				"High Blood Pressure", "Fracture", "Allergy", "Covid-19", "Asthma", "Infection", "Eye Problem",
				"Skin Disease" };

		System.out.println("Select your health issue:");
		for (int i = 0; i < issues.length; i++) {
			System.out.println((i + 1) + ". " + issues[i]);
		}
		System.out.print("Enter your choice (1-" + issues.length + "): ");
		int issueChoice = scanner.nextInt();
		scanner.nextLine();

		String problem;
		if (issueChoice >= 1 && issueChoice <= issues.length) {
			problem = issues[issueChoice - 1];
		} else {
			System.out.println("Invalid choice. Defaulting to 'General Checkup'.");
			problem = "General Checkup";
		}

		String id = "PAT" + (patients.size() + 1);
		Patient patient = new Patient(id, name, age, gender, contact, problem);
		patients.add(patient);

		System.out.println("Patient registered successfully. ID: " + id);
	}

	static void scheduleAppointment() {
		System.out.print("Enter patient ID: ");
		String patientId = scanner.nextLine();

		System.out.print("Enter doctor name: ");
		String doctor = scanner.nextLine();

		System.out.print("Enter appointment date (YYYY-MM-DD): ");
		String date = scanner.nextLine();

		String[] timeSlots = { "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "2:00 PM", "3:00 PM", "4:00 PM" };

		System.out.println("Available Time Slots:");
		for (int i = 0; i < timeSlots.length; i++) {
			String slot = timeSlots[i];
			boolean isBooked = false;
			for (Appointment a : appointments) {
				if (a.doctor.equalsIgnoreCase(doctor) && a.date.equals(date) && a.time.equalsIgnoreCase(slot)) {
					isBooked = true;
					break;
				}
			}
			System.out.println((i + 1) + ". " + slot + (isBooked ? " (Booked)" : ""));
		}

		System.out.print("Select a time slot (1-" + timeSlots.length + "): ");
		int slotChoice = scanner.nextInt();
		scanner.nextLine();

		if (slotChoice < 1 || slotChoice > timeSlots.length) {
			System.out.println("Invalid time slot selection.");
			return;
		}

		String selectedTime = timeSlots[slotChoice - 1];

		for (Appointment a : appointments) {
			if (a.doctor.equalsIgnoreCase(doctor) && a.date.equals(date) && a.time.equalsIgnoreCase(selectedTime)) {
				System.out.println("This time slot is already booked by another patient.");
				return;
			}
		}

		String id = "APT" + (appointments.size() + 1);
		Appointment appointment = new Appointment(id, patientId, doctor, date, selectedTime);
		appointments.add(appointment);

		System.out.println("Appointment scheduled successfully. ID: " + id);
	}

	static void addStaff() {
		System.out.print("Enter staff name: ");
		String name = scanner.nextLine();

		String[] roles = { "Doctor", "Nurse", "Admin", "Receptionist", "Technician", "Pharmacist" };

		System.out.println("Select staff role:");
		for (int i = 0; i < roles.length; i++) {
			System.out.println((i + 1) + ". " + roles[i]);
		}
		System.out.print("Enter your choice (1-" + roles.length + "): ");
		int roleChoice = scanner.nextInt();
		scanner.nextLine();

		if (roleChoice < 1 || roleChoice > roles.length) {
			System.out.println("Invalid role choice. Staff not added.");
			return;
		}

		String role = roles[roleChoice - 1];

		String id = "STF" + (staffList.size() + 1);
		Staff staff = new Staff(id, name, role);
		staffList.add(staff);

		System.out.println("Staff member added successfully. ID: " + id);
	}

	static void viewPatients() {
		System.out.println("\nRegistered Patients:");
		for (Patient p : patients) {
			System.out.println(p);
		}
	}

	static void viewAppointments() {
		System.out.println("\nScheduled Appointments:");
		for (Appointment a : appointments) {
			System.out.println(a);
		}
	}

	static void viewStaff() {
		System.out.println("\nHospital Staff:");
		for (Staff s : staffList) {
			System.out.println(s);
		}
	}

	static class Patient {
		String id, name, gender, contact, problem;
		int age;

		public Patient(String id, String name, int age, String gender, String contact, String problem) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.contact = contact;
			this.problem = problem;
		}

		public String toString() {
			return id + " | " + name + " | " + age + " | " + gender + " | " + contact + " | Problem: " + problem;
		}
	}

	static class Appointment {
		String id, patientId, doctor, date, time;

		public Appointment(String id, String patientId, String doctor, String date, String time) {
			this.id = id;
			this.patientId = patientId;
			this.doctor = doctor;
			this.date = date;
			this.time = time;
		}

		public String toString() {
			return id + " | Patient ID: " + patientId + " | Doctor: " + doctor + " | Date: " + date + " | Time: "
					+ time;
		}
	}

	static class Staff {
		String id, name, role;

		public Staff(String id, String name, String role) {
			this.id = id;
			this.name = name;
			this.role = role;
		}

		public String toString() {
			return id + " | " + name + " | " + role;
		}
	}
}