# NewsGPT Integration

NewsGPT Integration is a project that leverages OpenAI's GPT-4-turbo model to create an interactive chat agent on a Pepper robot. The project consists of two main components: an Android app and a Python script. These components communicate via WebSocket to provide real-time interactions.

![Alt text](pepper-talking-with-people.jpg)


## Project Structure

- **Android App**: Runs on the Pepper robot, handling user interactions and speech synthesis.
- **Python Script**: Acts as the backend chat agent, processing user input and generating responses using GPT-4-turbo.

## Features

- Real-time communication between Pepper robot and the backend.
- Speech synthesis for natural interactions.
- Integration with OpenAI's GPT-4-turbo for intelligent responses.

### Prerequisites

- Android Studio for developing and running the Android app.
- Python 3.8 or later for running the Python script.
- Required Python libraries: `nltk`, `tensorflow`, `flask`, `websockets`.
- OpenAI API key for accessing GPT-4-turbo.
- NewsAPI key for accessing news data.

### Android App Setup

1. Open the project in Android Studio.
2. Ensure you have the necessary SDKs and dependencies installed.
3. Build and run the project on your Pepper robot.

### Python Script Setup

1. Create a virtual environment:
    ```bash
    python3 -m venv env
    source env/bin/activate
    ```
2. Install the required packages:
    ```bash
    pip install nltk tensorflow flask websockets
    ```
3. Download NLTK data:
    ```python
    import nltk
    nltk.download('stopwords')
    ```

### Setting Up API Keys

1. Obtain your OpenAI API key from [OpenAI](https://www.openai.com).
2. Obtain your NewsAPI key from [NewsAPI](https://newsapi.org).
3. Set the API keys as environment variables:
    ```bash
    export OPENAI_API_KEY='your-openai-api-key'
    export NEWSAPI_KEY='your-newsapi-key'
    ```

4. Modify your Python script to load the API keys from the environment:
    ```python
    import os
    openai_api_key = os.getenv('OPENAI_API_KEY')
    newsapi_key = os.getenv('NEWSAPI_KEY')
    ```

### Running the Android App

1. Deploy the app to your Pepper robot.
2. Ensure the robot is connected to the same network as your backend server.
3. Open the app on your robot.

### Running the Python Script

1. Navigate to the directory containing the Python script.
2. Run the python notebook cell by cell to train the model a

### Establishing Connection

The Android app and the Python script communicate via WebSocket. Ensure the WebSocket server address in the Android app matches the address of the server running the Python script.