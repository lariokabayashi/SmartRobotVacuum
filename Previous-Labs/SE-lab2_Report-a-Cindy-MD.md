# Software Engineering (SE) Lab 2 Report on Requirements Engineering (RE)

### Authors

| Family name | First name(s) | Student ID | *Partner A or B* |  
| --- | --- | --- | --- |
| Um | Cindy | Partner A (Robot App) |  
| Moniruzzaman | MD | Partner B (Robot Firmware) |

### Version 
WIP (work in progress) or PREP or FINAL

**Deadlines for this document**

- lab 2 preparation: Friday, April 25, earlier PREP appreciated
- lab 2 final version: Friday, May 2, earlier FINAL appreciated


***Prerequisites:***

| What you check/do now | Why and for what pupose |
 | --- | --- |
| Intro slide set accessed | *Slide on diagram drawing -> by hand (foto for lab 2 prep) vs. by tool (lab 2 final report), slides on GIT* |
| RE slide set accessed | *Version 02a contains Slides on Ivan Marsic's IEEE-style requirements list, tracabilityy matrix etc.*| 
| UC slide set accessed | UML UC diagram syntax and semantics |
| git installed | Clone (first time) or pull (later) your gitlab! If this file with correct name is not yet available by your partner, git add, commit and push it! Later commit and push your inputs! |

***Tasks***:
- Fulfill Prerequisites (above) **FIRST**!
- Each team of two provides an original report without plagiarism, see regulations on lab 1 task sheet!
- Rename this template **"SE-lab2-Report_*team-Number_names-of-the-two*"**, upload it (first time by one partner) to gitlab or pull it (later) and commit changes continuously! 
- Transfer suitable content from lab 1 report into the new structure of this report and refine/complete it according to the subtasks given at the top of each section!

## Glossary

*This glossary only contains terms related to the application domain. A glossary of SE-related terms is placed into appendix B*

| Term | Acronym | Description |  
| --- | --- | --- |
| Robot Vacuum | RV | Robotic Vacuum Cleaner |
| ... | ... | ... |


## 1. Market Analysis for Robot Vacuum Cleaner

In my efforts to conduct a market analysis and identify typical product features for a robotic vacuum cleaner system and its app, I focused on several key areas. First, I examined the current market overview, including market size, growth projections, key players, and emerging trends. This helped in understanding the competitive landscape and consumer preferences. 

Next, I analyzed consumer demographics to identify the target audience and their specific needs. I then conducted a competitive analysis to compare features, pricing strategies, and strengths and weaknesses of existing products. For the robotic vacuum cleaner system, I identified essential features such as advanced navigation, surface adaptation, safety mechanisms, operational modes, and communication capabilities. For the app, I highlighted control functions, mapping and planning features, connectivity, user interface, and failure handling mechanisms. The detailed information retrieved from various sources is compiled in Appendix A.

### 1b. Innovative Feature: Set Virtual Boundaries
**Description**: Allow users to draw virtual boundaries on a map of their home by defining “no-go” zones and drawing virtual boundaries on a digital map of their home via a mobile app. E.g. Parents can draw virtual boundaries on a map of their home to keep the robot out of specific areas where children play or where delicate items are stored.

**Acceptance Criteria**: The robot respects these boundaries and avoids the designated areas. Users can edit and remove boundaries as needed via the mobile app.


<img src="images/mobile_app.png" alt="Conceptual Image of Mobile App Interface" width="400" />

**Hardware Components**:
- Mapping and Navigation Module: This module enables the robot to create and update a map of its environment. It can be implemented using various technologies such as lidar, stereo cameras, or SLAM (Simultaneous Localization and Mapping) algorithms.
- Localization Module: This module provides accurate location information of the robot within the mapped environment. It can be implemented using GPS, lidar, camera-based, or other localization technologies.
- Wireless Communication Module: This module enables the robot to receive updates and commands from the user's mobile app. It can be implemented using Wi-Fi, Bluetooth, or other wireless communication protocols.

<img src="images/hardware.png" alt="Conceptual Image of Robot with liDAR System" width="500" />

**System Architecture**:
- Robot: The robot is equipped with a mapping and navigation module, localization module, and wireless communication module.
- Mobile App: The mobile app allows users to create, edit, and manage virtual boundaries on a map of their home or workspace.
- Cloud Server (optional): The cloud server can be used to store and manage the maps, virtual boundaries, and robot settings.


## 2a. Functional Requirements for the App (Partner A)

| Feature / Requirement               | Relevance             | Kano Classification       |
|------------------------------------|------------------------|---------------------------|
| Voice Command Integration          | Both (App + Firmware)  | Exciter / Delighter       |
| Customizable Cleaning Profiles     | App                    | Performance               |
| Remote Monitoring (e.g., live feed)| App                    | Performance               |
| Scheduled Cleaning                 | App                    | Must-Be                   |
| Set Virtual Boundaries             | App                    | Exciter / Delighter       |
| Cleaning Reports                   | App                    | Performance               |
| Integration with Home Security     | Both (App + Firmware)  | Exciter / Delighter       |

*Validated List of functional requirements (like Ivan Marsic's IEEE-style requirements list) extracted from appendix A, **extended by your individual innovative feature**.  Add a column with (refind!) Kano classification! Validated means that this list is checked w.r.t. necessary criteria, see the lecture slides.*

## 2b. Functional Requirements for the Firmware (Partner B)


| Feature / Requirement                         | Relevance         | Kano Classification |
|------------------------------------------------|--------------------|----------------------|
| Secure Robot-to-App Communication             | Firmware           | Must-Be              |
| Automatic Return to Charging Station          | Firmware           | Must-Be              |
| Obstacle Detection and Avoidance              | Firmware           | Performance          |
| Dynamic Suction Adjustment Based on Surface   | Firmware           | Exciter/Delighter    |
| Firmware Update Over-The-Air (OTA)             | Firmware           | Must-Be              |
| Emergency Stop Handling (Button/Command)      | Firmware           | Must-Be              |
| Cleaning Session Logging and Reporting        | Firmware           | Performance          |
| Self-Diagnosis and Fault Reporting             | Firmware           | Performance          |
| Battery Management and Optimization           | Firmware           | Must-Be              |

---

*Partner B according to list of authors in the document header*

*Cf. to description of section 2a. Adjust the ones that apply to both (app and firmware) for the firmware here.* 

## 3. Non-Functional Requirements (Partner B)

| Non-Functional Requirement                                               | Relevance  | Type             |
|---------------------------------------------------------------------------|------------|------------------|
| Firmware must securely encrypt communication data (TLS or equivalent).   | Firmware   | Security         |
| Robot must respond to obstacle detection within 0.5 seconds.              | Firmware   | Performance      |
| Robot must resume cleaning automatically after recharge.                 | Firmware   | Reliability      |
| Robot must complete firmware updates without user intervention.          | Firmware   | Maintainability  |
| System must survive power outages during update and rollback if needed.  | Firmware   | Robustness       |
| Firmware should minimize energy usage in standby mode.                   | Firmware   | Efficiency       |

---

*Cf. to description of section 2a. Add a column that indicates relevance for the app (i.e. mobil app) and/or the firmware (i.e. robot software)!*

## 4. Stakeholder List (Partner B)


| Stakeholder          | Role/Expectation                                        |
|----------------------|----------------------------------------------------------|
| End User              | Operate robot safely and efficiently.                   |
| Firmware Developer    | Develop secure, efficient robot software.               |
| Product Manager       | Define features and prioritize firmware requirements.   |
| Quality Assurance Team| Test firmware stability, safety, and performance.        |
| Customer Support      | Assist users during firmware-related issues.             |
| Regulatory Body       | Ensure compliance with safety standards.                 |
| App Developer         | Interface coordination between app and firmware.         |

---

*Include a column with the role/expectation on the stakeholder's contribution. Refined your stakeholder list to cover your innovative feature"

## 5a. Use Cases (UCs) of the App software (Partner A)

- List of UCs with short UC-description
- Tracability matrix
- UC diagram
- Extended UC description for 2 UCs of your individual, original choice
- for the final version: activity diagram of the detailled UCs

## List of User Stories (Partner A)
| Identifier | User Case| 
|------------|------------|
| UC1 | As a user, I want the vacuum to detect and avoid small objects (like toys or cables) using liDAR. |
| UC2 | As a user, I want to set up zone-specific cleaning preferences (e.g., more suction in the kitchen) from the app. |
| UC3 | As a user, I want the robot to recognize different floor types and automatically adjust its cleaning mode. |
| UC4 | As a user, I want the vacuum to notify me if it gets stuck and suggest possible solutions in the app and through the voice assistant. |
| UC5 | As a user, I want the vacuum to support pet-friendly mode to reduce noise and avoid chasing animals. |
| UC6 | As a user, I want the app to recommend maintenance tips based on my usage patterns. |
| UC7 | As a user, I want to create no go zones for my robot by drawing on the map of the mobile app (e.g., child napping zone). |
| UC8 | As a user, I want to enable energy-saving mode where the robot prioritizes low power usage during cleaning. |
| UC9 | As a user, I want to immediately stop the vacuum using a physical button or in-app command so I can prevent damage or address unexpected situations quickly. |
| UC10 | As a user, I want to be able to login to the app with authentication. |
| UC11 | As a user, I want to control the vacuum using voice commands via smart assistants like Alexa or Google Assistant. |
| UC12 | As a user, I want to view a live video feed from the vacuum to monitor cleaning remotely. |
| UC13 | As a user, I want to schedule cleaning sessions in advance, specifying times and zones. |
| UC14 | As a user, I want to receive a cleaning summary after each session, showing coverage and duration. |

## Traceability Matrix (Partner A)

| Requirement ID | Description                                      | Covered Use Case(s)     |
|----------------|--------------------------------------------------|--------------------------|
| REQ-1          | Voice Command Integration                        | UC11, UC4                     |
| REQ-2          | Customizable Cleaning Profiles                   | UC2                      |
| REQ-3          | Remote Monitoring (e.g., live feed)              | UC12                     |
| REQ-4          | Scheduled Cleaning                               | UC13                     |
| REQ-5          | Set Virtual Boundaries                           | UC7                      |
| REQ-6          | Cleaning Reports                                 | UC14                     |
| REQ-7          | Integration with Home Security                   | UC4, UC6                 |


## UC Diagram (Partner A)
<img src="images/uc_diagram.png" alt="UC Diagram for Robot App" width="800" />

## Extended Use Case Descriptions (Partner A): UC-7 No Go Zones (Virtual Boundaries)
- **Related Requirements:** REQ-07, REQ-08  
- **Initiating Actor:** End User  
- **Goal:** Allow users to add, modify, and remove virtual boundaries (“no-go” zones) on a map of their home via a mobile app to prevent the robot from entering specific areas.  
- **Participating Actors:** Robot Vacuum, Mobile App  
- **Preconditions:** Robot has completed an initial mapping of the home; mobile app is installed and paired with the robot; user has access to the app.  
- **Postconditions:** Robot successfully avoids the designated virtual boundaries during cleaning sessions; users can view, edit, and delete boundaries in real time.

**Flow of Events:**
1. ← User opens the mobile app and navigates to the map interface.
2. ← User draws a virtual boundary or "no-go" zone by marking areas on the map.
3. → App saves the boundary definition and syncs it to the robot.
4. → Robot updates its navigation map to include the virtual boundary constraints and starts cleaning session.
5. → When approaching a defined boundary, the robot re-routes to avoid the restricted area.
6. → Robot completes cleaning while respecting all active virtual boundaries.
7. → Once cleaning has been completed user receives mobile app notification.


**Extensions (Alternate Scenarios):**
- 2a. User defines an overlapping or invalid boundary → App notifies user and requests correction.
- 4a. Sync fails due to connectivity issue → App shows sync error and retries automatically or prompts user to reconnect.
- 6a. Robot is unable to re-route due to narrow paths or fully blocked access → Robot stops, sends alert to user, and logs issue.
- 8a. User deletes all boundaries → Robot reverts to standard navigation mode.

**How it Works:**
- The robot uses SLAM (Simultaneous Localization and Mapping) to generate a digital floor map during its initial exploration.
- This map is displayed in the companion mobile app, allowing users to interact with it.
- Virtual boundaries are defined by the user through touch gestures (e.g., drawing rectangles or polygons) on the app interface.
- Once a boundary is defined, it is transmitted to the robot and stored locally in its navigation map.
- During cleaning, the robot continuously localizes itself in real time and cross-references its position against the stored virtual boundaries.
- If the robot approaches a no-go zone, it dynamically re-plans its path to stay outside the restricted area.
- Users can edit or delete these boundaries at any time; changes are synced instantly (if online) or at the start of the next session.

## Activity diagram of the detailled UCs (Partner A)
<img src="images/activity_diagram.png" alt="UC Diagram for Robot App" width="800" />

## 5b. Use Cases (UCs) of the Firmware software (Partner B)

- List of UCs with short UC-description
- Tracability matrix
- UC diagram
- Extended UC description for 2 UCs of your individual, original choice
- for the final version: activity diagram of the detailled UCs

### List of Firmware Use Cases

| UC ID  | Use Case Title                  | Short Description |
|--------|----------------------------------|-------------------|
| UC-F1  | Secure Data Communication        | Encrypt and send data to the app securely. |
| UC-F2  | Automatic Return to Charging     | Detect low battery and return to the dock. |
| UC-F3  | Obstacle Detection and Avoidance | Detect obstacles and navigate around them. |
| UC-F4  | Dynamic Suction Adjustment       | Adjust suction power based on floor surface. |
| UC-F5  | Firmware Update via OTA          | Update firmware remotely through the app. |
| UC-F6  | Emergency Stop Handling          | Stop robot safely when emergency command/button is activated. |
| UC-F7  | Session Logging                  | Log cleaning session data for app display. |
| UC-F8  | Self-Diagnosis                   | Detect internal faults and notify the app. |

---


### Traceability Matrix (Firmware)

| Requirement                          | UC-F1 | UC-F2 | UC-F3 | UC-F4 | UC-F5 | UC-F6 | UC-F7 | UC-F8 |
|--------------------------------------|-------|-------|-------|-------|-------|-------|-------|-------|
| Secure Communication                 |   X   |       |       |       |       |       |       |       |
| Return to Dock                       |       |   X   |       |       |       |       |       |       |
| Obstacle Detection                   |       |       |   X   |       |       |       |       |       |
| Dynamic Suction                      |       |       |       |   X   |       |       |       |       |
| OTA Firmware Update                  |       |       |       |       |   X   |       |       |       |
| Emergency Stop                       |       |       |       |       |       |   X   |       |       |
| Cleaning Session Logging             |       |       |       |       |       |       |   X   |       |
| Self-Diagnosis and Fault Reporting   |       |       |       |       |       |       |       |   X   |

---


### Firmware UC Diagram Text Based

| **Initiator** | **Use Case**                  | **Participant(s)**          |
|---------------|--------------------------------|------------------------------|
| Robot         | Secure Data Communication      | App Backend                  |
| Robot         | Automatic Return to Charging   | Charging Station             |
| Robot         | Obstacle Detection and Avoidance| Environment (Obstacles)      |
| Robot         | Dynamic Suction Adjustment     | Floor Surface Sensors        |
| Robot         | Firmware Update via OTA        | Cloud Server                 |
| User/Robot    | Emergency Stop Handling        | Firmware Emergency Module    |
| Robot         | Session Logging                | Internal Storage             |
| Robot         | Self-Diagnosis                 | App Backend                  |

---

#### UC-F2: Automatic Return to Charging Station

| Attribute              | Description |
|------------------------|-------------|
| **Use Case Name**       | Automatic Return to Charging |
| **Related Requirements**| Battery Management, Energy Efficiency |
| **Initiating Actor**    | Robot Firmware |
| **Actor's Goal**        | Ensure robot returns safely to the dock for recharging. |
| **Participating Actors**| Charging Station |
| **Preconditions**       | Battery level falls below 20%. Dock is available and reachable. |
| **Postconditions**      | Robot is successfully docked and recharging. |
| **Flow of Events (Main Scenario)** | 1. Monitor battery continuously.<br>2. Detect low battery threshold.<br>3. Search and navigate to docking station.<br>4. Align with dock.<br>5. Begin charging. |
| **Extensions (Alternate Scenarios)** | 2a. Dock not reachable → Try 3 retries.<br>3a. Obstacle in path → Navigate around or find alternate route. |

---

#### UC-F6: Emergency Stop Handling

| Attribute              | Description |
|------------------------|-------------|
| **Use Case Name**       | Emergency Stop Handling |
| **Related Requirements**| Safety Compliance |
| **Initiating Actor**    | User / Robot Sensor |
| **Actor's Goal**        | Immediately stop all robot operations during emergency. |
| **Participating Actors**| Motor Controller, Navigation Module |
| **Preconditions**       | Robot must be running an operation. |
| **Postconditions**      | Robot operations halted safely. Error mode activated. |
| **Flow of Events (Main Scenario)** | 1. Emergency button press detected or emergency stop command received.<br>2. Robot immediately halts all motors.<br>3. Sends emergency stop notification to App.<br>4. Awaits manual reset before restarting operations. |
| **Extensions (Alternate Scenarios)** | 1a. Sensor malfunction → Trigger hard stop fallback using backup systems. |

---



## 6a. App User Stories (Partner A)

*Refined list of lab 1. Consider the scenario-based approach for MVPs within an agile project.*

| Identifier | User Story |
|------------|------------|
| UST-17 | As a user, I want to set up zone-specific cleaning preferences (e.g., more suction in the kitchen) from the app. |
| UST-19 | As a user, I want the vacuum to notify me if it gets stuck and suggest possible solutions in the app. |
| UST-20 | As a user, I want to view a heatmap of frequently cleaned vs. missed areas to identify neglected spots. |
| UST-21 | As a user, I want to set "do not disturb" hours during which the robot will not clean or send notifications. |
| UST-24 | As a user, I want the app to recommend maintenance tips based on my usage patterns. |
| UST-25 | As a user, I want to receive seasonal cleaning suggestions (e.g., spring deep clean reminders) through the app. |
| UST-26 | As a user, I want to temporarily block cleaning in an area without redrawing the map (e.g., child napping). |
| UST-27 | As a user, I want to use my phone's camera to help the robot map a new room more accurately. |
| UST-28 | As a user, I want to generate a custom cleaning report (PDF) that I can download or share. |
| UST-29 | As a user, I want to assign custom sound profiles to the robot for various events (e.g., startup, finish). |
| UST-31 | As a user, I want to immediately stop the vacuum using a physical button or in-app command so I can prevent damage or address unexpected situations quickly. |

## 6.b Firmware User Stories (Partner B)

*Refined list of lab 1. Consider the scenario-based approach for MVP within an agile project.*

### References

*Include here from all references only the ones that you check on request by your market analysis result (appendix A) and that you use for your requirements lists, include citation number there!*

*Include furthermore all methodological references and further references that you use, e.g. the slides, including slide number of your citation.*

1. Technavio, "Robotic Vacuum Cleaner Market Growth Analysis - Size and Forecast 2025-2029," Technavio, 2025. [Online]. Available: https://www.technavio.com/report/robotic-vacuum-cleaner-market-industry-analysis. [Accessed: Sep. 12, 2023].
2. Mordor Intelligence, "Robot Vacuum Cleaners Market Analysis | Industry Growth, Size & Forecast Report," Mordor Intelligence, 2025. [Online]. Available: https://www.mordorintelligence.com/industry-reports/robot-vacuum-cleaners-market. [Accessed: Sep. 12, 2023].
3. Future Market Insights, "Robotic Vacuum Cleaners Market Share & Market Insights," Future Market Insights, 2025. [Online]. Available: https://www.futuremarketinsights.com/reports/robotic-vacuum-cleaners-market-share-analysis. [Accessed: Sep. 12, 2023].
4. A. Stellman, "Requirements 101: User stories vs. use cases," Building Better Software, 2009. [Online]. Available: https://www.stellman-greene.com/2009/05/03/requirements-101-user-stories-vs-use-cases. [Accessed: Sep. 12, 2023].
5. B. Breiling, B. Dieber, and P. Schartner, "Secure Communication for the Robot Operating System," *Proceedings of the 11th IEEE International Systems Conference*, 2017. [Online]. Available: [https://www.researchgate.net/publication/314082519_Secure_Communication_for_the_Robot_Operating_System](https://www.researchgate.net/publication/314082519_Secure_Communication_for_the_Robot_Operating_System). [Accessed: Apr. 27, 2025].
6. A. Amini and S. Sulaiman, "CryptoROS: A Secure Communication Architecture for ROS," *International Journal of Advanced Computer Science and Applications*, vol. 8, no. 12, pp. 123–130, 2017. [Online]. Available: [https://www.semanticscholar.org/paper/CryptoROS%3A-A-secure-communication-architecture-for-Amini-Sulaiman/fbf773b4e930859da88f0836f9f729c6aa59d8df](https://www.semanticscholar.org/paper/CryptoROS%3A-A-secure-communication-architecture-for-Amini-Sulaiman/fbf773b4e930859da88f0836f9f729c6aa59d8df). [Accessed: Apr. 27, 2025].
7. S. Zhang, W. Li, X. Li, and B. Liu, "AuthROS: Secure Data Sharing Among Robot Operating Systems Based on Ethereum," *arXiv preprint arXiv:2208.14269*, 2022. [Online]. Available: [https://arxiv.org/abs/2208.14269](https://arxiv.org/abs/2208.14269). [Accessed: Apr. 27, 2025].
8. G. Restuccia, H. Tschofenig, and E. Baccelli, "Low-Power IoT Communication Security: On the Performance of DTLS and TLS 1.3," *arXiv preprint arXiv:2011.12035*, 2020. [Online]. Available: [https://arxiv.org/abs/2011.12035](https://arxiv.org/abs/2011.12035). [Accessed: Apr. 27, 2025].
9. J. Aumasson, "An Open Source Effort to Encrypt the Internet of Things," *WIRED*, Jan. 20, 2020. [Online]. Available: [https://www.wired.com/story/e4-iot-encryption](https://www.wired.com/story/e4-iot-encryption). [Accessed: Apr. 27, 2025].


## Appendix A (Market Analysis Documents)
The current market for robotic vacuum cleaners is experiencing significant growth, driven by increasing consumer demand for smart home devices and automation. Key players in the market include iRobot, Dyson, and Ecovacs [1,2]. Market trends indicate a strong preference for devices that integrate seamlessly with existing smart home environments, utilize advanced AI and machine learning for improved navigation and cleaning efficiency, and offer user-friendly interfaces [1].

Consumer demographics reveal that the primary target audience includes busy professionals, elderly individuals, and tech enthusiasts who value convenience and efficiency. These consumers prioritize features such as ease of use, effective cleaning performance, and smart home integration [2].

Competitive analysis shows that successful products in the market typically offer advanced navigation systems, adaptive surface behavior, robust safety features, multiple operational modes, and reliable communication capabilities [3]. Pricing strategies vary, with premium models offering more advanced features and budget models focusing on essential functionalities [2].

Recent research emphasizes the increasing importance of cybersecurity in home robotics, highlighting the adoption of secure protocols (TLS/DTLS) and blockchain-based solutions to protect data integrity during communication and firmware updates [5,6,7,8]. 

Furthermore, competitive models increasingly support secure Over-the-Air (OTA) firmware updates, dynamic obstacle avoidance, automatic return to charging stations, and integrated emergency stop mechanisms, ensuring both operational safety and user convenience [7,8,9].


## Appendix B (SE Glossary)

| Term | Acronym | Description |  
| --- | --- | --- |
| ... | INVEST | ... |
| Priority Classification | MuSCoW | Must - Should - Could - Won't |
| Requirements Engineering | RE | All activities needed to cover all of the aspects of the RE phase |
| Requirements Validation | ReqV | All activities needed to verify and validate the list of requirements |
| Validation | ... | ... |
| Verification | ... | ... |

