# github-useractivity
# GitHub User Activities CLI

## 📌 Overview
This is a simple **Command Line Interface (CLI) tool** written in Java that fetches and displays a user's recent GitHub activities from the GitHub API. The tool filters and shows only relevant events from the last **3 months**.

## 🚀 Features
- Accepts a **GitHub username** as input from the user.
- Fetches the user's **public GitHub activity** from the GitHub API.
- **Filters** events to show only the **last 3 months** of activity.
- Displays the **event type** and **date** of each relevant event.
- Includes **exception handling** for API errors and invalid responses.

## 🛠️ Prerequisites
- Java **8+** installed on your machine
- Internet connection (to fetch data from GitHub API)

## 📥 Installation & Usage

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/tjothiprakash/github-user-activities-cli.git
cd github-user-activities-cli
```

### 2️⃣ Compile the Java Files
```sh
javac GitHubUserActivitiesCLI.java GitHubAPIHandler.java GitHubAPIException.java
```

### 3️⃣ Run the CLI
```sh
java GitHubUserActivitiesCLI
```

### 4️⃣ Enter a GitHub Username
Once prompted, enter the **GitHub username** to fetch activities:
```
Enter GitHub username: octocat
```

### 5️⃣ Example Output
```
Recent GitHub Activities (Last 3 Months):
- PushEvent on 2025-02-10
- IssuesEvent on 2025-02-06
- ForkEvent on 2025-01-28
```

## 🏗️ Project Structure
```
📂 github-user-activities-cli
│── GitHubUserActivitiesCLI.java      # Main entry point for CLI
│── GitHubAPIHandler.java             # Handles API requests & filtering
│── GitHubAPIException.java           # Custom exception handling
│── README.md                         # Project documentation
```

## ⚠️ Error Handling
- If the API request **fails**, an error message is displayed:
  ```
  API Error: Failed to fetch data. Response Code: 403
  ```
- If the username **does not exist**, it handles the response gracefully.

## 📜 License
This project is licensed under the **MIT License**.

## 🤝 Contributing
Feel free to submit **pull requests** or report **issues** if you find any bugs or want to improve the CLI.

## 💡 Author
Developed by **[TJOTHIPRAKASH]** 🚀

---
✅ **Enjoy tracking GitHub activities from the CLI!** 🎯
https://github.com/TJothiprakash/github-useractivity

https://roadmap.sh/projects/github-user-activity
