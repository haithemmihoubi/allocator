# Interview Task: Airport Parking Position Allocation

## Context
At airports, airplanes require a parking position during their ground time (between **arrival** and **departure**).  
For example, an aircraft arriving at *Istanbul Airport* at **12:00** and departing at **14:00** must be allocated a parking position for the duration of its stay.  

## Problem Statement
When processing a **park position request (ParkPositionRequest)**:
1. If the requested parking position is **available**, allocate it to the aircraft.  
2. If the requested parking position is **not available** (due to a time conflict), the system should check the other available park positions and allocate the **nearest available one**.  
3. If no parking positions are available, the system should return **an empty value (`Optional.empty`)**.  

## Constraints
- A parking position can host only **one aircraft at a given time**.  
- An aircraft can occupy at most **one parking position**.  
- Overlapping (time-conflicting) requests cannot be assigned to the same parking position.  
- If multiple positions are available, the **nearest one** (based on the `location` value) should be selected.  

## Task
- You are provided with the `InterviewQuestion.java` file.  
- Your task is to complete the **`question1` method** according to the rules above.  
- A ready-to-use test class `TestCaseQ1.java` will automatically validate your solution.  
- If all test cases pass successfully, your implementation is considered correct.  

## Technical Details
- **JDK 11+**  
- **JUnit 5**  
- **Lombok** (optional, may be used)  
