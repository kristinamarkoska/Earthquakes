# Earthquakes Application

A Spring Boot REST API application that fetches, stores, filters, and displays real-time earthquake data from the USGS GeoJSON API.

---

## 📌 Project Description

This project is a backend system with a simple frontend that retrieves earthquake data from the USGS API, processes it, stores it in a PostgreSQL database, and provides filtering and visualization options.

The system demonstrates REST API design, database integration, and frontend-backend communication.

---

## ⚙️ Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- REST API
- HTML, CSS, JavaScript
- Bootstrap

---

## 🚀 Features

- Fetch latest earthquake data from USGS API  
- Parse GeoJSON response (magnitude, place, time, title, magType)  
- Store earthquake data in PostgreSQL database  
- Automatically delete old records before inserting new ones (avoid duplicates)  
- Filter earthquakes with magnitude greater than 2.0  
- Filter earthquakes after a specific time  
- Delete individual earthquake records  
- Display data in table format (frontend)  
- Auto-refresh data every 10 seconds  
- Automatic time formatting in frontend  

---
## ▶️ How to Run the Project

### Backend

Open project in IntelliJ IDEA

Ensure Java 17+ is installed

Run EarthquakesApplication.java

Backend runs on:

http://localhost:8080

### Frontend

Open index.html in browser

Make sure backend is running

Use buttons to fetch and filter data
## 📡 API Endpoints

### Fetch Data from USGS

## 🗄️ Database Setup

Make sure PostgreSQL is installed and running.

Create database:

```sql
CREATE DATABASE earthquakes;


