# Software Engineering (SE) Lab 1 Report

### Authors

| Family name | First name(s) | *Partner A or B* |  
| --- | --- | --- |
| Um | Cindy | Partner A |  
| Moniruzzaman | MD | Partner B |

*Partner A will consider the Robot App in more detail, partner B the robot firmware*

## Glossary

| Term | Acronym | Description |  
| --- | --- | --- |
| Robot Vacuum | RV | Robotic Vacuum Cleaner |
| ... | ... | ... |


## 1. Market Analysis for Robot Vacuum Cleaner

In my efforts to conduct a market analysis and identify typical product features for a robotic vacuum cleaner system and its app, I focused on several key areas. First, I examined the current market overview, including market size, growth projections, key players, and emerging trends. This helped in understanding the competitive landscape and consumer preferences. 

Next, I analyzed consumer demographics to identify the target audience and their specific needs. I then conducted a competitive analysis to compare features, pricing strategies, and strengths and weaknesses of existing products. For the robotic vacuum cleaner system, I identified essential features such as advanced navigation, surface adaptation, safety mechanisms, operational modes, and communication capabilities. For the app, I highlighted control functions, mapping and planning features, connectivity, user interface, and failure handling mechanisms. The detailed information retrieved from various sources is compiled in Appendix A.


## 2. List of Features and Requirements

*From the appendix, extract a list of features that are requirements relevant for the software. Add a column that indicates relevance for the app (i.e. mobil app), the firmware (i.e. robot software) or for both!*
| Feature / Requirement               | Relevance             |
|------------------------------------|------------------------|
| Self-Cleaning Mechanism            | Firmware               |
| Voice Command Integration          | Both (App + Firmware)  |
| Safety Features (e.g., anti-fall)  | Firmware               |
| Customizable Cleaning Patterns     | Both                   |
| Local Wi-Fi Communication          | Both                   |
| Multi-Floor Mapping                | Firmware               |
| Cleaning Reports                   | App                    |
| Customizable Cleaning Profiles     | App                    |
| Energy Efficiency Mode             | Firmware               |
| Remote Monitoring (e.g., live feed)| App                    |
| Scheduled Cleaning                 | App                    |
| Home Security Integration          | Both                   |
| Set Virtual Boundaries             | App                    |


---

## 3. Stakeholder List for Requirements Engineering Stage:
- **End Users**: Individuals who will use the robotic vacuum cleaner, including busy professionals, pet owners, parents, tech enthusiasts, frequent travelers, and new users.
- **Software Developers**: Tasked with developing the app and integrating features such as scheduling, remote monitoring, and virtual boundaries.
- **Customer Support**: Provides assistance to users, handles inquiries, and resolves issues related to the robotic vacuum cleaner and app.
- **Product Managers**: Responsible for defining the product vision and strategy and ensuring the product meets market needs
- **Marketing and Sales Teams**: Understanding market trends and customer needs and developing strategies to promote the product
- **Legal and Compliance Teams**: Ensuring the product complies with regulations and standards

## 4. Kano Classification
| Feature                             | Kano Category         |
|-------------------------------------|------------------------|
| Voice Command Integration           | Exciter / Delighter    |
| Safety Features                     | Must-Be                |
| Multi-Floor Mapping                 | Performance            |
| Self-Cleaning Mechanism             | Exciter / Delighter    |
| Cleaning Reports                    | Performance            |
| Customizable Cleaning Profiles      | Performance            |
| Scheduled Cleaning                  | Must-Be                |
| Energy Efficiency Mode              | Exciter                |
| Home Security Integration           | Exciter / Delighter    |
| Remote Monitoring                   | Performance            |
| Set Virtual Boundaries              | Exciter / Delighter    |

---

## 5. App User Stories (Partner A: Cindy Um)
### User Story: Schedule Cleaning [2,4]
- **Name**: Sarah
- **Occupation**: Busy Professional
- **Characteristics**: Sarah is a busy professional who works long hours and values efficiency and convenience. She has a busy schedule and appreciates the ability to automate cleaning tasks to keep her home clean and organized. Sarah uses scheduling features in apps to ensure her home is cleaned regularly without needing to remember to start the robot manually.

**Summary**: Users can schedule their cleaning robot to clean their home at specific times and receive notifications about the cleaning status.

**Alternative Paths**:
- In Step 3, the user may choose to edit or delete an existing schedule. The app allows the user to make changes and saves the updated schedule.
- In Step 5, if the cleaning robot encounters an issue (e.g., low battery, obstacle), the app notifies the user and provides troubleshooting steps.
- The user may decide to manually start or stop the cleaning at any time, overriding the schedule.

**Postconditions**: The cleaning robot has cleaned the home at the scheduled times, and the user has received notifications about the cleaning status.

**Users**: All users, especially busy professionals.

**Preconditions**: The cleaning robot is connected to the app and the user has the app installed on their device.

**Basic Course of Events**:
1. The user opens the app and navigates to the scheduling feature.
2. The app prompts the user to set specific days and times for cleaning.
3. The user selects the desired days and times and confirms the schedule.
4. The app saves the schedule and displays a confirmation message.
5. At the scheduled times, the cleaning robot starts cleaning automatically.
6. The user receives a notification before the cleaning starts.
7. The user receives a notification after the cleaning is completed

**Acceptance Criteria**:
- The app allows users to set specific days and times for cleaning.
- The robot starts cleaning at the scheduled times.
- Users receive notifications before and after scheduled cleanings.

### User Story: Set Virtual Boundaries [2,4]
- **Name**: Michael
- **Occupation**: Parent
- **Characteristics**: Michael has young children who play in specific areas of the house. He is concerned about the robotic vacuum cleaner disturbing their playtime or entering areas with delicate items. Michael needs the ability to set virtual boundaries to keep the robot out of certain zones.

**Acceptance Criteria**:
- The app allows users to draw virtual boundaries on a map of their home.
- The robot respects these boundaries and avoids the designated areas.
- Users can edit and remove boundaries as needed.

## 6. Firmware User Stories (Partner B:MD Moniruzzaman)

*Partner B according to list of authors in the document header*
### User Story: Adaptive Surface Cleaning

**Name**: Alex  
**Occupation**: Tech Enthusiast  

**Summary**: Robot detects floor types and adjusts cleaning accordingly.

**Acceptance Criteria**:
- Detects floor transitions.
- Adjusts suction/brush speed.
- Reports changes to the app.

**Basic Flow**:
1. Starts cleaning.
2. Detects surface type.
3. Adjusts parameters.
4. Logs behavior.

---

### User Story: Obstacle Avoidance and Safety

**Name**: Emma  
**Occupation**: Parent  

**Summary**: Robot avoids small objects and prevents falls.

**Acceptance Criteria**:
- Avoids obstacles and stairs.
- Alerts app if stuck.

**Basic Flow**:
1. Robot starts.
2. Detects obstacles.
3. Reroutes or stops.
4. Sends alert if unresolved.

---

### References (IEEE)
1. Technavio, "Robotic Vacuum Cleaner Market Growth Analysis - Size and Forecast 2025-2029," Technavio, 2025. [Online]. Available: https://www.technavio.com/report/robotic-vacuum-cleaner-market-industry-analysis. [Accessed: Sep. 12, 2023].
2. Mordor Intelligence, "Robot Vacuum Cleaners Market Analysis | Industry Growth, Size & Forecast Report," Mordor Intelligence, 2025. [Online]. Available: https://www.mordorintelligence.com/industry-reports/robot-vacuum-cleaners-market. [Accessed: Sep. 12, 2023].
3. Future Market Insights, "Robotic Vacuum Cleaners Market Share & Market Insights," Future Market Insights, 2025. [Online]. Available: https://www.futuremarketinsights.com/reports/robotic-vacuum-cleaners-market-share-analysis. [Accessed: Sep. 12, 2023].
4. A. Stellman, "Requirements 101: User stories vs. use cases," Building Better Software, 2009. [Online]. Available: https://www.stellman-greene.com/2009/05/03/requirements-101-user-stories-vs-use-cases. [Accessed: Sep. 12, 2023].



## Appendix A

#### Market Analysis
The current market for robotic vacuum cleaners is experiencing significant growth, driven by increasing consumer demand for smart home devices and automation. Key players in the market include iRobot, Dyson, and Ecovacs [1,2]. Market trends indicate a strong preference for devices that integrate seamlessly with existing smart home environments, utilize advanced AI and machine learning for improved navigation and cleaning efficiency, and offer user-friendly interfaces [1].

Consumer demographics reveal that the primary target audience includes busy professionals, elderly individuals, and tech enthusiasts who value convenience and efficiency. These consumers prioritize features such as ease of use, effective cleaning performance, and smart home integration [2].

Competitive analysis shows that successful products in the market typically offer advanced navigation systems, adaptive surface behavior, robust safety features, multiple operational modes, and reliable communication capabilities [3]. Pricing strategies vary, with premium models offering more advanced features and budget models focusing on essential functionalities [2].

##### Robot System Features
1. **Self-Cleaning Mechanism**: Develop a self-cleaning brush and filter system that automatically removes hair and debris, reducing maintenance efforts.
2. **Voice Command Integration**: Enable voice control through popular smart home assistants like Alexa, Google Assistant, and Siri, allowing users to start, stop, and schedule cleaning tasks using voice commands.
3. **Safety Features**: Includes mechanisms to prevent falls and ensure secure movement over various terrains.
4. **Customizable Cleaning Patterns**: Allow users to set specific cleaning patterns and routes based on their home layout, ensuring thorough coverage of all areas.
5. **Communication**: Uses local WIFI for communication with the app and provides regular status updates.
6. **Multi-Floor Mapping**: Enable the robot to create and store maps for multiple floors, allowing seamless transitions between different levels of the home.

##### Robot App Features
1. **Cleaning Reports**: Provide detailed cleaning reports that include maps of cleaned areas, time spent, and dirt levels detected, helping users monitor cleaning efficiency
2. **Customizable Cleaning Profiles**: Allow users to create and save multiple cleaning profiles for different rooms or types of messes, such as "kitchen cleanup" or "pet hair removal."
3. **Energy Efficiency Mode**: Introduce an energy-saving mode that optimizes cleaning routes and power usage, extending battery life
4. **Remote Monitoring**: Enable users to monitor the robot's progress in real-time from anywhere, with live video feed and status updates.
5. **Scheduled Maintenance Alerts**: Send notifications for scheduled maintenance tasks, such as brush and filter replacements, ensuring optimal performance.
6. **Integration with Home Security**: Sync the robot with home security systems to provide additional surveillance and alert users of any unusual activity detected during cleaning.
