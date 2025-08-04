# Software Engineering (SE) Lab 2 Report on Requirements Engineering (RE)

### Authors

| Family name | First name(s) | Student ID | *Partner A or B* |  
| --- | --- | --- | --- |
| Okabayashi | Larissa | 2827931 | Partner A (Robot App)|  
| Im | Brian | 2827056 | Partner B (Robot Firmware)|

### Version 
FINAL

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
| Robot Mower | RM | Robotic Lawn Mower |
| Robot Vacuum | RV | Robotic Vacuum Cleaner |
| ... | ... | ... |


## 1. Market Analysis for Robotic Vacuum Cleaner 

*Give a short description of your efforts, put the relevant retrieved information in the Appendix A*

To conduct a comprehensive market analysis and identify typical product features for robotic vacuum cleaners and their associated apps, I focused on current market dynamics, leading technologies, and consumer-oriented trends. The global market has seen significant growth, reaching around $9–9.4 billion in 2024 and projected to expand to $31.8 billion by 2033, driven by rising urbanization, increased disposable income, and the growing adoption of smart home devices [1], [2], [3].

I analyzed key players and regional performance, identifying Asia Pacific as the dominant market, with top brands like iRobot, Ecovacs, and Roborock holding 50–65% of the global share. Emerging competitors are also gaining traction by offering features such as AI-powered navigation and self-emptying capabilities [4].

For the robotic vacuum cleaner system, I identified core features like advanced lidar or SLAM-based navigation, multi-sensor integration, real-time mapping, and efficient battery and dustbin systems. On the app side, essential capabilities include multi-floor mapping, scheduling, zone-based cleaning, real-time monitoring, voice assistant support, and user-friendly controls. These features reflect broader consumer expectations for automation, customization, and seamless connectivity.

The detailed comparative analysis between brands such as bObsweep and Marstak, and the identification of hardware and software features, is documented in Appendix A.

## 1b. Innovative Feature (Partner A):

*Introduce your individual, special innovative robot feature and give a short description of the required hardware that you will assume for RE of the software to be, possibly with image(s) that makes clear how the robot can provide that feature.*

### Intelligent Detection and Cleaning of Tough Messes

Overview:
This advanced feature equips your vacuum robot with the capability to intelligently detect, classify, and clean difficult messes such as pet waste, liquid spills, food debris, or dense clumps of dust and hair. Unlike traditional robotic vacuums that follow preset routines, this system leverages real-time sensor fusion and adaptive behavior to determine the type and severity of a mess. Based on the analysis, it dynamically adjusts its cleaning strategy or alerts the user when manual intervention is the safer or more effective choice.

#### How the System Works

1. **Environmental Perception via Sensor Fusion**  
   The robot combines data from multiple sensors to build a real-time, high-resolution map of its surroundings. This allows it to distinguish floor types, detect obstacles, and understand what kind of mess it's dealing with.

2. **AI-Based Classification and Decision Logic**  
   Instead of relying on fixed datasets, the system uses Visual Language Models (VLMs) that can interpret sensor input and images in a more flexible way. It identifies objects like pet waste, tangled cables, or spilled liquids, ranks them by priority, and decides how to respond accordingly.

3. **Adaptive Cleaning Behavior**  
   Depending on the type of mess and its location:
   - The robot avoids smearing pet waste by rerouting.
   - It ramps up suction and brush torque for carpeted areas.
   - For spills, it switches to mopping and disables suction to prevent damage.
   - Edge tracking is used to clean tightly around obstacles like furniture legs.

4. **Coordinated Navigation and Execution**  
   After generating a response plan, the robot navigates using edge-following algorithms. In multi-unit setups, robots share real-time heatmaps to cover high-traffic areas more efficiently and avoid redundant cleaning paths.

5. **User Feedback and Auto-Maintenance**  
   The system pushes alerts to the companion app when human intervention is needed (e.g., large puddles). It also handles routine tasks like dustbin emptying and returns to the dock for charging with high accuracy thanks to smart battery monitoring.

#### Key Hardware Components

- **Vision Systems**  
  - Includes RGB and RGBD cameras for object detection  
  - Equipped for low-light conditions  
  - Uses direct ToF LiDAR for detailed 3D spatial mapping

- **Ultrasonic Time-of-Flight Sensors**  
  - Help detect floor material changes and measure surface hardness

- **Dust and Moisture Sensors**  
  - Conductivity sensors detect liquids  
  - Airflow sensors assess dustbin fill level and debris load

- **Inertial Measurement Units (IMUs)**  
  - Maintain accurate positioning even if the robot is moved or tilted

- **Brush and Suction Control Modules**  
  - Adjust torque and speed based on surface conditions in real time

- **Edge AI Hardware and Microcontrollers**  
  - Handle real-time VLM inference and system logic locally

- **Wireless Communication Interfaces**  
  - Support multi-bot coordination and user app integration via Wi-Fi/Bluetooth

- **Battery and Docking Hardware**  
  - Advanced SoC monitoring and precise auto-docking

![Conceptual Image of Intelligent Detection and Cleaning of Tough Messes ](images/img.png)

## 2a. Functional Requirements for the App (Partner A)

*Partner A according to list of authors in the document header*

*Validated List of functional requirements (like Ivan Marsic's IEEE-style requirements list) extracted from appendix A, **extended by your individual innovative feature**.  Add a column with (refind!) Kano classification! Validated means that this list is checked w.r.t. necessary criteria, see the lecture slides.*

| ID     | Feature                                  | Description                                                                                         | Relevance | Kano Classification      |
|--------|------------------------------------------|-----------------------------------------------------------------------------------------------------|-----------|---------------------------|
| REQ-1  | Bot Control                               | Start, stop, return to dock, or trigger self-empty from the app                                     | App       | Must-be                   |
| REQ-2  | Mapping                                   | Save and edit floor maps, with auto room division and multi-level support                          | Both      | Must-be                   |
| REQ-3  | Scheduling                                | Set cleaning schedules per room or floor, with recurring or custom time slots                       | Both      | One-dimensional           |
| REQ-4  | Cleaning Preferences                      | Define suction levels, mopping modes, and brush behavior per surface or room                        | Both      | One-dimensional           |
| REQ-5  | Cleaning History                          | View past cleaning sessions, paths taken, messes encountered, and resolutions applied               | App       | Attractive                |
| REQ-6  | Zone/No-Go Zones                          | Mark keep-out areas or high-priority zones on the floor plan                                        | Both      | Must-be                   |
| REQ-7  | Smart Control                             | Integration with voice assistants (e.g., Siri, Google Assistant, Alexa)                             | Both      | One-dimensional           |
| REQ-8  | Dark Mode                                 | Switch app UI between light and dark themes                                                         | App       | Indifferent               |
| REQ-9  | Help Center                               | Access tutorials, FAQs, and live support within the app                                             | App       | Must-be                   |
| REQ-10 | Authentication & User Accounts            | Support login/signup (email, social login), with secure sessions and cloud backup of preferences    | App       | Must-be                   |
| REQ-11 | Multi-User Support                        | Allow multiple users to share control of the same vacuum unit, with role-based permissions          | App       | One-dimensional           |
| REQ-12 | Notifications & Alerts                    | Push notifications for cleaning status, stuck situations, full dustbin, or critical issues          | App       | Must-be                   |
| REQ-13 | Robot Status Dashboard                    | Live updates on battery, active mode, location, detected messes, and environment status             | App       | Attractive                |
| REQ-14 | Firmware Update via App                   | Update robot firmware OTA directly from app with progress tracking                                  | App       | Must-be                   |
| REQ-15 | Localization & Language Settings          | Multi-language support, temperature/time units, and region settings                                 | App       | Indifferent               |
| REQ-16 | Privacy & Data Management                 | In-app consent options, access to recorded data, and opt-out of cloud-based data storage            | App       | Must-be                   |
| REQ-17 | Intelligent Detection and Classification                    | AI classifies mess types (e.g., liquid, pet waste, hair, cables) using real-time camera and sensor data | Both      | Attractive                |
| REQ-18 | Mess Severity-Based Prioritization                          | Robot dynamically decides whether to avoid, clean with suction/mop, or send alert to user               | Both      | Attractive                |
| REQ-19 | Real-Time Obstacle Alerts                                   | User receives app notification (≤ 500ms latency) when unmanageable or sensitive messes are detected     | App       | One-dimensional           |
| REQ-20 | Adaptive Cleaning Mode Configuration                        | User can enable/disable specific intelligent actions (e.g., auto-mopping for spills) per mess category  | App       | One-dimensional           |
| REQ-21 | Heatmap and Cleaning Statistics per Mess Type               | View where most messes are detected over time and see system behavior per class (e.g., liquid vs hair)  | App       | Attractive                |
| REQ-22 | Feedback Loop to Improve AI Recognition                     | Users can label misidentified messes to improve onboard model personalization                           | App       | Attractive                |

## 2b. Functional Requirements for the Firmware (Partner B)

*Partner B according to list of authors in the document header*

*Cf. to description of section 2a. Adjust the ones that apply to both (app and firmware) for the firmware here.* 

| ID     | Feature              | Description                                                                 | Relevance | Kano Classification      |
|--------|----------------------|-----------------------------------------------------------------------------|-----------|---------------------------|
| REQ-2  | Mapping               | Scans floor whilst cleaning, creates map using data                         | Both      | Must-be                   |
| REQ-3  | Scheduling            | Set cleaning schedules per room or floor, with recurring or custom time slots | Both      | One-dimensional           |
| REQ-4  | Cleaning Preferences  | Set suction power, change nozzle width                                      | Both      | One-dimensional           |
| REQ-6  | Zone/No-Go Zones      | Track current robot location, avoid defined areas                           | Both      | Must-be                   |
| REQ-7  | Smart Control         | Onboard buttons, voice assistant on robot                                   | Both      | One-dimensional           |
| REQ-23 | Navigation            | Use SLAM or lidar for real-time movement and detecting obstacles            | Firmware  | Must-be                   |
| REQ-24 | Boot/Mapping          | Quick startup and initial room division                                     | Firmware  | Must-be                   |
| REQ-25 | Models                | Support variations (e.g., obstacle avoidance, self-emptying)                | Firmware  | Indifferent               |
| REQ-26 | Dustbin/Docking       | Self-emptying dustbin with smart docking                                    | Firmware  | One-dimensional           |
| REQ-27 | Cleaning Modes        | Change cleaning modes between suction, brush and mopping accordingly        | Firmware  | Attractive                |
| REQ-28 | Battery               | Long runtime and quiet performance                                          | Firmware  | Must-be                   |
| REQ-29 | Efficient Architecture| Onboard processing with single-chip autonomy                                | Firmware  | Must-be                   |
| REQ-30 | Surface Detection     | Automatic detection of different floor types                                | Firmware  | Attractive                |
| REQ-31 | Liquid detection      | Conductivity sensors detect liquids and can map the area                    | Firmware  | Attractive                |

## 3. Non-Functional Requirements (Partner B)

*Cf. to description of section 2a. Add a column that indicates relevance for the app (i.e. mobil app) and/or the firmware (i.e. robot software)!*

| ID     | Feature              | Description                                                                 | Relevance |Type      |
|--------|----------------------|-----------------------------------------------------------------------------|-----------|---------------------------|
| REQ-32 | Cleaning Speed       | Robot cleans at a minimum average rate of 30m²/h                        | Firmware  | Performance Requirement    |
| REQ-33 | Battery Life         | From full charge the robot can clean 100m²                                  | Firmware  | Dependability Requirement  |
| REQ-34 | Noise Level          | Average robot noise level during operation is below 70 decibels             | Firmware  | Performance Requirement    |
| REQ-35 | Wireless Control     | Robot responds the controls from the App within 2 seconds                   | Both      | Performance Requirement    |
| REQ-36 | Obstacle Recognition | Robot avoids physical contact above speed of 0.1m/s with obstacles          | Firmware  | Safety Requirement         |
| REQ-37 | Data Encryption      | User data, including settings and logs, is encrypted                        | App       | Security Requirement       |
| REQ-38 | Power Efficient      | Robot does not use more than 40 watts of power during operation         | Firmware  | Environmental Requirement  |
| REQ-39 | Firmware Memory      | Robot firmware utilises less than 120mB of memory                       | Firmware  | Development Requirement    |
| REQ-40 | Accessibility        | Robot can be controlled through voice controls and has braille buttons  | Both      | Ethical Requirement        |
| REQ-41 | Charging Speed       | Robot takes maximum 2 hours to charge to full battery                       | Firmware  | Performance Requirement    |
| REQ-42 | App Loading Time     | App loads within 2 seconds after opening                                    | Firmware  | Usability Requirement      |
| REQ-43 | Quick Stop           | Robot stops all movements within 0.5 seconds of pressing on/off button      | Firmware  | Safety Requirement         |

## 4. Stakeholder List (Partner A)

*Include a column with the role/expectation on the stakeholder's contribution. Refined your stakeholder list to cover your innovative feature"

| Stakeholder               | Role / Contribution                                                                                     |
|---------------------------|----------------------------------------------------------------------------------------------------------|
| **End User**              | Purchases and uses the robot vacuum. Provides real-world feedback on mess detection and cleaning accuracy. |
| **Manufacturer**          | Designs and builds the hardware, including sensors (e.g., cameras, LiDAR, moisture) and embedded AI chips. |
| **Software Developer**    | Builds and maintains the mobile app, cloud APIs, and local firmware logic supporting mess classification and real-time user alerts. |
| **Embedded Systems Engineer** | Integrates sensor fusion algorithms and real-time AI classification on the vacuum’s onboard hardware. |
| **AI/ML Engineer**        | Trains and fine-tunes models for visual/lidar-based mess classification and adaptive cleaning decisions. |
| **Customer Support**      | Handles user inquiries related to cleaning behavior, misclassifications, or unexpected system responses. |
| **Marketing Team**        | Promotes the vacuum’s intelligent cleaning capabilities, especially the smart classification of tough messes. |
| **Vendors**               | Distribute and sell the product through retail and online channels, requiring product education to inform customers about its capabilities. |
| **Carpet Care Experts**   | Offer domain knowledge to improve detection and care protocols for surface-sensitive messes like hair or liquids on fabric surfaces. |
| **Privacy & Security Consultants** | Ensure data collected from cameras and sensors is anonymized, encrypted, and complies with regulations (e.g., GDPR, CCPA). |
| **UX/UI Designer**        | Designs intuitive app interfaces for configuring cleaning preferences, reviewing mess heatmaps, and providing feedback to the AI system. |
| **Test Engineers / QA**   | Ensure robust performance of mess detection features across diverse household environments (e.g., lighting, surfaces, clutter). |

## 5a. Use Cases (UCs) of the App software (Partner A)

- List of UCs with short UC-description
- Tracability matrix
- UC diagram
- Extended UC description for 2 UCs of your individual, original choice
- for the final version: activity diagram of the detailled UCs

### List of Use Cases (UCs)

| UC ID   | Use Case Name                                | Description                                                                                 |
|---------|-----------------------------------------------|---------------------------------------------------------------------------------------------|
| UC-1    | Control Robot                                 | User starts, stops, sends to dock, or sends to self-empty via the app.                      |
| UC-2    | Schedule Cleaning                             | User creates or edits a scheduled cleaning routine by room, time, and preferences.          |
| UC-3    | Define Zones                                  | User sets or updates cleaning and no-go zones on the saved floor map.                       |
| UC-4    | View Cleaning History                         | User accesses detailed logs of past sessions and live progress tracking.                    |
| UC-5    | Manage Notifications                          | User receives real-time alerts and configures notification preferences.                     |
| UC-6    | Voice Assistant Integration                   | User integrates robot control via Alexa, Google Assistant, or Siri.                         |
| UC-7    | Update Firmware                               | User updates firmware through the app to receive bug fixes or feature enhancements.         |
| UC-8    | Share Access                                  | User invites and manages access for other household members through individual accounts.    |
| UC-9    | User Authentication                           | Users securely register, log in, or log out of their account on the mobile app.             |
| UC-10   | Manage User Profile                           | User updates personal info, password, or privacy preferences.                               |
| UC-11   | Intelligent Mess Detection                    | Robot detects, classifies, and prioritizes complex messes using AI models.                  |
| UC-12   | Adaptive Cleaning Execution                   | Robot executes different cleaning strategies based on mess classification and environment.  |
| UC-13   | Review Intelligent Cleaning Logs              | User reviews heatmaps and insights on detected messes and cleaning effectiveness.           |
| UC-14   | Configure Cleaning Preferences                | User sets room-specific cleaning modes (suction level, mopping, avoidance rules, etc.).     |
| UC-15   | Dark Mode & Accessibility Settings            | User personalizes app appearance and accessibility options.                                 |
| UC-16   | Access Help Center                            | User views FAQs, submits issues, or contacts customer support.                              |

### Traceability Matrix

| Requirement ID | Description                                                        | Covered Use Case(s)       |
|----------------|--------------------------------------------------------------------|----------------------------|
| REQ-1          | Bot control via app                                                | UC-1                       |
| REQ-2          | Mapping, auto room division, multi-level support                   | UC-3, UC-14                |
| REQ-3          | Scheduling per room/time                                           | UC-2                       |
| REQ-4          | Cleaning preferences (suction, mode, etc.)                         | UC-14                      |
| REQ-5          | History logs and real-time progress                                | UC-4, UC-13                |
| REQ-6          | Zone / No-go zone definition                                       | UC-3                       |
| REQ-7          | Voice assistant integration                                        | UC-6                       |
| REQ-8          | Dark mode in app                                                   | UC-15                      |
| REQ-9          | Help center access                                                 | UC-16                      |
| REQ-10         | User authentication and login                                      | UC-9                       |
| REQ-11         | Firmware updates via the app                                       | UC-7                       |
| REQ-12         | User profile and privacy settings                                  | UC-10                      |
| REQ-13         | Notifications (e.g. bin full, filter warning, critical errors)     | UC-5                       |
| REQ-14         | Multi-user access                                                  | UC-8                       |
| REQ-17         | **Intelligent Detection and Cleaning of Tough Messes**             | **UC-11, UC-12, UC-13**    |

### UC Diagram 

- **Actors**: End User (Primary), Robot (Secondary), Voice Assistant (Secondary), Manufacturer (Secondary)
- **System**: Robot Vacuum App

![Visual Use Case Diagram](images/Frame.png)

### Extended Use Case Descriptions

#### UC-1: Control Robot

- **Related Requirements**: REQ-1, REQ-4  
- **Initiating Actor**: End User  
- **Goal**: Start, stop, or dock the robot through the app.  
- **Participating Actors**: Robot  
- **Preconditions**: App is paired and robot is online.  
- **Postconditions**: Robot begins action (cleaning or docking).  

**Flow of Events:**
1. → User opens the app and selects a control option.  
2. ← App sends command to robot.  
3. ← Robot starts the corresponding task (clean, stop, dock).  
4. ← App confirms action with live status.

**Extensions (Alternate Scenarios):**
- 1a. Robot not reachable → App shows “Connection Error.”
- 3a. Obstacle prevents docking → App notifies user.

### Extended Use Case Description for UC-11: Intelligent Mess Detection

- **Related Requirements:** REQ-11, REQ-12  
- **Initiating Actor:** Robot Vacuum  
- **Goal:** Automatically detect, classify, and prioritize complex messes (e.g., pet waste, liquid spills, dense debris) using onboard AI models.  
- **Participating Actors:** End User (notified if intervention is needed), App  
- **Preconditions:** Robot is powered on, sensors are operational, and cleaning session is active.  
- **Postconditions:** Mess is detected and classified; robot adapts cleaning strategy or alerts user if manual intervention is required.

**Flow of Events:**
1. → Robot begins cleaning session and activates sensor suite (camera, LiDAR, moisture, dust, etc.).
2. → Robot continuously scans environment for anomalies or messes.
3. → Robot’s AI model analyzes sensor data to detect and classify mess type (e.g., liquid, pet waste, food debris).
4. → Robot assigns a priority/severity level to the detected mess.
5. → If the mess is within safe cleaning parameters, robot adapts its cleaning strategy (e.g., increases suction, switches to mopping).
6. → If the mess is unsafe or requires manual intervention (e.g., pet waste), robot pauses and sends a real-time alert to the user via the app.
7. ← User receives notification and can choose to acknowledge, instruct avoidance, or intervene manually.
8. → Robot logs the detection and action taken for future review.

**Extensions (Alternate Scenarios):**
- 2a. Sensor malfunction detected → Robot pauses, notifies user, and logs error.
- 3a. Mess cannot be classified with high confidence → Robot avoids area, flags for user review.
- 5a. Adaptive cleaning fails (e.g., spill too large) → Robot stops, notifies user, and marks area as uncleaned.
- 6a. User does not respond to alert → Robot avoids area and resumes cleaning elsewhere.

### Activity Diagram 
##### Activity Diagram for UC-1: Control Robot
![Activity Diagram for UC-1: Control Robot](<images/UML activity diagram-2.png>)

##### Activity Diagram for UC-11: Intelligent Mess Detection
![Activity Diagram for UC-11: Intelligent Mess Detection](<images/intelligence.png>)

## 5b. Use Cases (UCs) of the Firmware software (Partner B)

- List of UCs with short UC-description
- Tracability matrix
- UC diagram
- Extended UC description for 2 UCs of your individual, original choice
- for the final version: activity diagram of the detailled UCs

### List of Use Cases (UCs)

| UC ID   | Use Case Name             | Description                                                                 |
|---------|---------------------------|-----------------------------------------------------------------------------|
| UC-17   | Control Robot             | User starts, stops, or docks the vacuum via buttons                         |
| UC-18   | Autonomous Navigation     | Robot covers all floor areas moving, using floor map if available for path finding |
| UC-19   | Voice Control             | User assigns functions to the robot via voice                               |
| UC-20   | Update Firmware           | User updates firmware of the robot vacuum                                   |
| UC-21   | Automatic Charging        | Robot autonomously returns to charging dock on low battery                  |
| UC-22   | Adjust Cleaning Intensity | User can adjust the robot vacuum power between strong or weak               |
| UC-23   | Quick Boot                | User can boot on the robot cleaner quickly for a quick vacuum               |
| UC-24   | Automatic Mapping         | Robot scans and stores a map of the cleaning area during cleaning           |
| UC-25   | Obstacle Dectection       | SLAM and LIDAR Sensor detects obstacle and makes gentle contact to confirm then reroutes   |
| UC-26   | Auto Resume Cleaning      | Robot returns to area it was last cleaning after charging to continue       |
| UC-27   | Self Emptying             | Robot empties bin into larger container in dock after it fills up           |
| UC-28   | Emergency Stop            | User can immediately stop the robot's actions by pressing the on/off button |
| UC-29   | Liquid Spill              | Conductivity sensor detects wet area causing robot to change mode from vacuum to mop     |
| UC-30   | Cleaning Mode Change      | Robot switches between vacuum, brushing and mopping depending on floor type |
| UC-31   | Scheduled Cleaning        | Robot begins cleaning in the scheduled area and time from the app           |
| UC-32   | Identify Floor Type       | Ultrasonic sensor detects a change in floor type                            |

### Traceability Matrix

| Requirements | UC-17 | UC-18 | UC-19 | UC-20 | UC-21 | UC-22 | UC-23 | UC-24 | UC-25 | UC-26 | UC-27 | UC-28 | UC-29 | UC-30 | UC-31 | UC-32 |
|----------------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|-------|
| REQ-2          |       | X     |       |       |       |       |       | X     | X     |       |       |       |       |       |       |       |
| REQ-3          |       | X     |       |       | X     | X     |       | X     | X     | X     | X     | X     | X     | X     | X     | X     |
| REQ-4          |       |       | X     |       |       | X     |       |       |       |       |       |       |       |       |       |       |
| REQ-6          |       | X     |       |       |       |       |       |       | X     |       |       |       |       |       |       |       |
| REQ-7          | X     |       | X     |       |       |       |       |       |       |       |       |       |       |       |       |       |
| REQ-23         |       | X     |       |       |       |       |       | X     | X     |       |       |       |       |       |       |       |
| REQ-24         |       |       | X     | X     |       |       | X     |       |       |       |       |       |       |       |       |       |
| REQ-26         |       | X     |       |       | X     |       |       |       |       |       | X     |       |       |       |       |       |
| REQ-27         |       |       |       |       |       |       |       |       |       |       |       |       | X     | X     |       | X     |
| REQ-30         |       |       |       |       |       |       |       |       |       |       |       |       | X     | X     |       | X     |
| REQ-31         |       |       |       |       |       |       |       |       |       |       |       |       | X     |       |       |       |
| REQ-36         |       | X     |       |       |       |       |       |       | X     |       |       |       |       |       |       |       |
| REQ-43         | X     |       | X     |       |       |       |       |       |       |       |       | X     |       |       |       |       |

| Requirement ID | Description                                            |
|----------------|--------------------------------------------------------|
| REQ-2          | Autonomous Mapping                                     |
| REQ-3          | Scheduled Cleaning                                     |
| REQ-4          | Adjust suction power                                   |
| REQ-6          | Track current position and avoid defined areas         |
| REQ-7          | Onboard buttons and voice control                      |
| REQ-23         | Navigation with SLAM or LIDAR                          |
| REQ-24         | Quick start up and updating firmware                   |
| REQ-26         | Docks automatically with self emptying dustbin         |
| REQ-27         | Adjust between vacuum, brush and mopping modes         |
| REQ-30         | Detect current cleaning floor type                     |
| REQ-31         | Conductivity sensor detects liquid                     |
| REQ-36         | Robot has soft contact with obstacles before rerouting |
| REQ-43         | Robot stops all movement after on/off button press or voice |

![Visual Use Case Diagram](images/uc_diagram2.png)

### Extended Use Case Descriptions

#### UC-21: Automatic Charging

- **Related Requirements**: REQ-26 
- **Initiating Actor**: Robot Battery  
- **Goal**: Battery is fully charged and robot resumes prior activity.  
- **Participating Actors**: Charging Dock
- **Preconditions**: Robot is low on battery and not charging.
- **Postconditions**: Robot resumes prior activity.

**Flow of Events:**
1. → Battery send notification that it is low.  
2. ← include::Autonomous Navigation(UC-18).  
3. ← Return to dock.
4. → Battery charges.
5. ← Return to prior location and resume previous activity.

**Extensions (Alternate Scenarios):**
User initiates include::Emergency Stop(UC-28).
1. ← The robot stops and stays in place.
2a. Autonomous Navigation(UC-18) returns no possible routes back.
1. ← The robot stops and alerts the user that it is stuck.
4a. The robot's litterbox is over 80% full.
1. → Charging dock empties out the litterbox.

#### UC-25: Obstacle Detection 

- **Related Requirements**: REQ-2, REQ-6, REQ-23, REQ-36
- **Initiating Actor**: Robot Sensor
- **Goal**: Detect obstacles and determine if it is movable.
- **Participating Actors**: None
- **Preconditions**: Robot is on and currently cleaning or moving.
- **Postconditions**: None worth mentioning.

**Flow of Events:**
1. → Sensor detects the blocked path. 
2. ← Robot slowly applies very slight pressure on blocking object.
3. → Object does not move.
4. ← Firmware is identified that an obstacle is confirmed in the given area.  

**Extensions (Alternate Scenarios):**
3a. Object shifts from the slight pressure.
1. ← The robot keeps lightly pushing the object.
2. → Object slips away to the side not blocking the path anymore.
<br><ensp>  2a. Object does not slip away. ← Firmware is identified that an obstacle is confirmed in the given area.
1. ← Robot continues on its original path.

### Activity Diagram 

##### Activity Diagram for UC-21: Automatic Charging
![Activity Diagram for UC-21: Automatic Charging](<images/UC21 activity diagram.png>)

##### Activity Diagram for UC-25: Control Robot
![Activity Diagram for UC-25: Obstacle Detection](<images/UC25 activity diagram.png>)

## 6a. App User Stories (Partner A)

*Refined list of lab 1. Consider the scenario-based approach for MVPs within an agile project.*

| Identifier | User Story |
|------------|------------|
| UST-1  | As a user, I will be able to start, stop, and pause the vacuum cleaner via the app. |
| UST-2  | As a user, I will be able to schedule cleaning sessions for specific times and days. |
| UST-3  | As a user, I will be able to view a live map of the cleaning progress in real-time. |
| UST-4  | As a user, I will be able to define and manage no-go zones and specific cleaning areas through the app. |
| UST-5  | As a user, I will be able to receive notifications when the cleaning is complete or if the robot encounters issues. |
| UST-6  | As a user, I will be able to view the robot’s cleaning history and performance over time. |
| UST-7  | As a user, I will be able to connect the app to voice assistants such as Alexa or Google Assistant. |
| UST-8  | As a user, I will be able to monitor battery status and charging progress through the app. |
| UST-9  | As a user, I will be able to remotely send the robot back to its charging dock. |
| UST-10 | As a user, I will be able to receive alerts when the dustbin is full or the filter needs replacement. |
| UST-11 | As a user, I will be able to update the robot’s firmware through the app when updates are available. |
| UST-12 | As a user, I will be able to switch between cleaning modes (e.g., quiet, turbo, auto) via the app. |
| UST-13 | As a user, I will be able to manage multiple devices and assign names to each vacuum cleaner. |
| UST-14 | As a user, I will be able to share device access with family members using the app. |
| UST-15 | As a user, I want the robot vacuum to intelligently detect and focus on tough messes (e.g., sticky spills, dense dirt) using AI-based mess classification, so that I can ensure deeper cleaning in areas that need it most.

## 6.b Firmware User Stories (Partner B)

*Refined list of lab 1. Consider the scenario-based approach for MVP within an agile project.*

| Identifier | User Story |
|------------|------------|
| UST-16 | As a user, I will be able to start, stop, and pause the vacuum cleaner through physical buttons on the robot. |
| UST-17 | As a user, I want the robot vacuum to safely return to the dock when the battery is low, so it can recharge autonomously. |
| UST-18 | As a user, I want the robot vacuum to remember where it left off after recharging so it can resume cleaning from the same spot. |
| UST-19 | As a user, I want the firmware to reliably store cleaning maps and store it locally for more efficient future cleaning paths. |
| UST-20 | As a user, I want the robot vacuum to automatically adjust suction power based on floor type to optimize performance and battery life. |
| UST-21 | As a user, I want the robot vacuum to stop cleaning immediately if it detects that it is stuck or entangled. |
| UST-22 | As a user, I want the firmware to prevent unsafe operation to ensure safety. |
| UST-23 | As a user, I want the firmware to autonomously determine room divisions in the software. |
| UST-24 | As a user, I will be able to control the robot vacuum through voice commands such as start, stop and pause. |
| UST-25 | As a user, I want the robot vacuum to navigate efficiently around obstacles and furniture while mapping the area, so it cleans the entire room systematically. |
| UST-26 | As a user, I want the robot vacuum to automatically empty its dustbin into a home station when full, so I don’t have to manually clean the robot's storage. |
| UST-27 | As a user, I want the robot vacuum to avoid obstacles like furniture, walls, or pets, so it doesn’t bump into things or get stuck during cleaning. |
| UST-28 | As a user, I want the robot vacuum to boot up and be ready to clean within seconds of being powered on, so I don’t have to wait for long startup times. |
| UST-29 | As a user, I want the robot vacuum to lower sounds during specific hours based on firmware settings. |

### References

*Include here from all references only the ones that you check on request by your market analysis result (appendix A) and that you use for your requirements lists, include citation number there!*

*Include furthermore all methodological references and further references that you use, e.g. the slides, including slide number of your citation.*

[1] Globenewswire, "Robotic Vacuum Cleaners Market Investment Opportunities and Strategy Outlook 2025–2033," Feb. 2025. [Online]. Available: https://www.globenewswire.com/news-release/2025/02/26/3032718/28124/en/Robotic-Vacuum-Cleaners-Market-Investment-Opportunities-and-Strategy-Outlook-2025-2033-Revenues-Worldwide-to-Cross-31-7-Billion-by-2033.html  
[2] The Business Research Company, "Robotic Vacuum Cleaners Global Market Report." [Online]. Available: https://www.thebusinessresearchcompany.com/report/robotic-vacuum-cleaners-global-market-report
[3] Research and Markets, "Robotic Vacuum Cleaners Market Report." [Online]. Available: https://www.researchandmarkets.com/reports/5735435/robotic-vacuum-cleaners-market-report  
[4] Future Market Insights, "Robotic Vacuum Cleaners Market Share Analysis." [Online]. Available: https://www.futuremarketinsights.com/reports/robotic-vacuum-cleaners-market-share-analysis  
[5] bObsweep, "bObsweep Models." [Online]. Available: https://www.bobsweep.com/models  
[6] Mordor Intelligence, "Robot Vacuum Cleaners Market." [Online]. Available: https://www.mordorintelligence.com/industry-reports/robot-vacuum-cleaners-market  
[7] bObsweep, "Compare Models." [Online]. Available: https://www.bobsweep.com/compare 

#### Methodological References

Renz, W. Requirements Engineering – SE_02a, HAW Hamburg, Slide 36: Kano Classification – used for classifying the refined functional requirements (REQ-1 to REQ-17) according to categories like Must-be, One-dimensional, Attractive, etc.

Renz, W. Requirements Engineering – SE_02a, HAW Hamburg, Slide 52: Requirements Validation Techniques – referenced for validating the final list of functional requirements against completeness, consistency, testability, etc.

Renz, W. Use Case Diagrams – SE_02b, HAW Hamburg, Slide 10: Deriving Use Cases from
System Requirements - referenced for creating List of UCs with short UC-description.

Renz, W. Use Case Diagrams – SE_02b, HAW Hamburg, Slide 11: Traceability Matrix - referenced for creating the Traceability Matrix of the Robot Cleaner Vacuum.

Renz, W. Use Case Diagrams – SE_02b, HAW Hamburg, Slide 15: Use Case Diagram - referenced for creating the Use Case Diagram of the Robot Cleaner Vacuum.

Renz, W. Use Case Diagrams – SE_02b, HAW Hamburg, Slide 21: Schema for Detailed Use Case Description - referenced for extending the Use Case Descriptions for Use Case 01 and Use Case 09.

## Appendix A (Market Analysis Documents)

### Market Analysis

The global robotic vacuum cleaner market is experiencing rapid expansion, driven by consumer demand for automated, time-saving cleaning solutions and the integration of advanced technologies. In 2024, the market reached approximately \$9–9.4 billion and is projected to grow to \$31.8 billion by 2033, with a compound annual growth rate (CAGR) of 14–17% [1], [2], [3]. Asia Pacific leads the market, holding over 40% of global revenue, fueled by strong adoption in both residential and commercial sectors [1], [6].

**Key Growth Drivers:**

- Rising adoption of smart home devices and AI-driven automation  
- Increasing disposable incomes and urbanization  
- Technological advancements in navigation, mapping, and connectivity  
- Expanding commercial and industrial applications  

**Market Structure:**

- Highly concentrated, with top brands (iRobot, Ecovacs, Roborock) controlling 50–65% of the market [4]  
- Strong competition from emerging brands offering AI navigation and self-emptying features  
- Residential segment dominates, but commercial use is growing rapidly  

**Trends:**

- Multi-room mapping, advanced sensors, and voice assistant integration  
- Customization, energy efficiency, and eco-friendly designs  
- Enhanced app-based controls and user experience  

### Product Comparison: bObsweep vs. Marstak Robot Vacuum Cleaners

| Feature Category | bObsweep | Marstak |
| :-- | :-- | :-- |
| **App Features** |  |  |
| Bot Control | Start/stop, send to charge/self-empty | Start/stop, send to charge/self-empty |
| Mapping | Save up to 3 floor maps, room division, NoSweep/NoMop zones | Auto room division, multi-level mapping |
| Scheduling | Custom by room and time | Multiple custom appointments (hourly, daily, weekly) |
| Cleaning Preferences | Suction power, cleaning behavior | Adjust suction per room, quiet mode |
| History Logs | Detailed cleaning activity | Real-time monitoring, live tracking |
| Zone/No-Go Zones | Set NoSweep/NoMop zones | Up to 30 zones and no-go areas (shared) |
| Smart Control | App, onboard buttons | App, onboard buttons, Alexa, Google Assistant |
| Extras | Dark mode, Help Center (coming soon), shopping | Voice assistant integration |

| Feature Category | bObsweep | Marstak |
| :-- | :-- | :-- |
| **Firmware/Hardware** |  |  |
| Navigation | SLAM-based, computer vision, real-time mapping | Lidar + 25 sensors, precise mapping |
| Boot/Mapping | 6-second boot, no training runs needed | Auto room division after first clean |
| Models | PetHair (pet focus), Dustin (self-emptying), Ultra-Vision (camera, obstacle avoidance) | Single model with advanced features |
| Dustbin/Docking | Self-emptying (Dustin, Ultra-Vision), 6L capacity | Smart dustbin docking, auto dirt disposal |
| Suction/Filters | Up to 8000 Pa (Ultra-Vision), multi-surface | High suction, advanced filters for allergens |
| Battery | Varies by model | 5200mAh, long runtime, quiet operation |
| Smart Assistants | App, onboard buttons | Alexa, Google Assistant |
| Efficiency | Single-chip for motor, sensor, navigation | Autonomous, efficient, customizable |

## Appendix B (SE Glossary)

| Term | Acronym | Description |  
| --- | --- | --- |
| User Story Qualities | INVEST | Independent - Negotiatable - Valuable - Estimatable - Small - Testable |
| Priority Classification | MuSCoW | Must - Should - Could - Won't |
| Requirements Engineering | RE | All activities needed to cover all of the aspects of the RE phase |
| Requirements Validation | ReqV | All activities needed to verify and validate the list of requirements |
| Validation | ... | Ensure the system provides the functions which best support the customer’s needs |
| Verification | ... | Ensure the functions are correctly implemented |