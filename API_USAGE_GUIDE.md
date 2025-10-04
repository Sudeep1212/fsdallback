# Events API Usage Guide

Your Spring Boot application runs at http://localhost:8080 and provides the following endpoints:

## Endpoints

### 1. Get All Events
- **URL**: `GET http://localhost:8080/Event`
- **Description**: Retrieves all events
- **Sample curl command**:
  ```
  curl -X GET http://localhost:8080/Event
  ```

### 2. Get Event by ID
- **URL**: `GET http://localhost:8080/Event/{id}`
- **Description**: Retrieves a specific event by ID
- **Sample curl command**:
  ```
  curl -X GET http://localhost:8080/Event/1
  ```

### 3. Create New Event
- **URL**: `POST http://localhost:8080/Event`
- **Description**: Creates a new event
- **Headers**: `Content-Type: application/json`
- **Sample curl command**:
  ```
  curl -X POST http://localhost:8080/Event \
    -H "Content-Type: application/json" \
    -d '{
      "event_name": "Tech Fest 2024",
      "event_start_date": "2024-04-15",
      "event_end_date": "2024-04-17",
      "event_time": "09:00 AM - 05:00 PM",
      "event_type": "Technical",
      "event_description": "Annual technical festival with coding competitions, robotics and workshops",
      "judge_id": "J001",
      "club_id": "C101"
    }'
  ```

### 4. Update Event
- **URL**: `PUT http://localhost:8080/Event/{id}`
- **Description**: Updates an existing event
- **Headers**: `Content-Type: application/json`
- **Sample curl command**:
  ```
  curl -X PUT http://localhost:8080/Event/1 \
    -H "Content-Type: application/json" \
    -d '{
      "event_name": "Updated Tech Fest 2024",
      "event_start_date": "2024-04-16",
      "event_end_date": "2024-04-18",
      "event_time": "10:00 AM - 06:00 PM",
      "event_type": "Technical",
      "event_description": "Updated description for the annual technical festival",
      "judge_id": "J001",
      "club_id": "C101"
    }'
  ```

## Common Errors and Solutions

### Error: "JSON parse error: Unexpected character"
This error occurs when the JSON body isn't formatted correctly. Make sure:
1. Your JSON keys and values are enclosed in double quotes
2. The JSON structure has proper commas between key-value pairs
3. You're using colons (not equals) between keys and values

### Error: "Required request body is missing" 
This error occurs when you don't send a JSON body with your POST or PUT request.
Make sure you're sending a properly formatted JSON body with your request.

## Testing with Postman

1. **Set the correct URL**: http://localhost:8080/Event
2. **Select the correct HTTP method**: GET, POST, or PUT
3. **For POST/PUT requests**:
   - Go to the "Body" tab
   - Select "raw"
   - Choose "JSON" from the dropdown
   - Enter a valid JSON body like the example above
4. **Click "Send"** 