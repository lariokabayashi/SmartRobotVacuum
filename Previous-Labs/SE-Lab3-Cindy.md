# Software Engineering (SE) Lab 3 Report on Detailed Design of App OR Firmware UC cluster

### Authors

| Family name | First name(s) | Student ID | *Partner* |  
| --- | --- | --- | --- |
| Um | Cindy | 2826963 | former team a |
| ... | ... | ... | former team b |

### Version 
WIP (work in progress)

**Deadlines for this document**

- lab 3 preparation: Monday, May 12, earlier PREP appreciated
- lab 3 final version: Monday, May 19, earlier FINAL appreciated


***Prerequisites:***

| What you check/do now | Why and for what pupose |
 | --- | --- |
| Domain modelling slide set accessed | *lecture 4b contains Slides on Ivan Marsic's domain analysis, tracabilityy matrix (2) etc.*| 
| UC slide set accessed | *UC clusters to be identified"  |
| git pulled | get last version of your partners work
| report 3 pushed | this document renamed and pushed by one of the partners |

**"SE-lab3-Report_*team-Number_AorB_names-of-the-two*"**, upload it (first time by one partner) to gitlab or pull it (later) and commit changes continuously! 

***Tasks***:
- Fulfill Prerequisites (above) **FIRST**!
- New team of partners A OR B provides an original report without plagiarism, see Appendix B and regulations on lab 1 task sheet!
- Transfer suitable content from lab 2: New team members compare their lab 2 final reports on App OR Firmware, identify problems, improve them and decide for a common basis for this lab, i.e. take lab 2 work, refine it and paste it in section 1 of this document.

## Glossary

*This glossary only contains terms related to the application domain. A glossary of SE-related terms is placed into appendix A*

| Term | Acronym | Description |  
| --- | --- | --- |
| Robot Mower | RM | Robotic Lawn Mower |
| Robot Vacuum | RV | Robotic Vacuum Cleaner |
| ... | ... | ... |

## 1. Base documents

### 1a. UC diagram

*Refined UC diagram with your UC-cluster indicated e.g. by color*
<img src="images/uc_diagram_clusters.png" alt="UC Diagram with highlighted clusters" width="800" />

The refined use case diagram for the Robot Vacuum App System can be grouped into three functional clusters. 

**Red: Smart Control & Personalization**  includes user-driven features like defining zones, managing notifications, pet-friendly mode, maintenance tips, no-go zones, authentication, and voice control. 

**Blue: Autonomous Navigation** covers the robot’s self-navigation capabilities, such as obstacle avoidance and floor type classification.** 

While not currently represented, a third cluster, **Green: System Efficiency & Energy**, could include future use cases like battery optimization and smart charging which would come from Partner B's firmware side. This clustering highlights the system’s structure and areas for potential growth.


*Shortly describe the way you improve and  decide.*

## 1b. Detailed UC Descriptions

*Refined Detailed UC Descriptions of UCs in your UC-cluster*

*Shortly describe the way you improve and  decide.*


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

## 1c. Activity Diagrams

*Copy your lab 2 Activity Diagrams here*

*Shortly describe if you still consider all or parts of them relevant for this labs work.*

<img src="images/activity_diagram.png" alt="UC Diagram for Robot App" width="800" />

Yes, most of the steps in the flowchart are still relevant for this lab’s work. They show how users set up and manage virtual boundaries through the app, and how the robot uses that information to clean efficiently. This supports key features like user control and smart navigation, which are important for the system.


## 2. First sketch and list of concepts for your domain model

*In the sketch, draw associations*

*in the list, give base responsibility of concept, i.e. prototypic class*

*Identify questions to be discussed* 
<img src="images/domain_model_draft.png" alt="Domain Model Draft" width="800" />


## 3. Refined tables of Responsibility, Associations and Properties

*As described in the lab taks sheet, I can recommend to use BlueJ for refining your paperwork of section 2 and finally extract all information for the tables from class javadoc and fields.*



## 4. Domain model diagram

*Prototypic class diagram with associations and properies.*

*Best edit your class diagram in BlueJ (best make arrows point right down), then take a screenshot and annotate associations to the arrows*

*Alternatively draw it by Visual Paradigm or other tool*



## 5. Tracability Matrix (2)

*Relates concepts to UCs*
| Requirement ID | Description                                      | Covered Use Case(s)     |
|----------------|--------------------------------------------------|--------------------------|
| REQ-1          | Voice Command Integration                        | UC11, UC4                     |
| REQ-2          | Customizable Cleaning Profiles                   | UC2                      |
| REQ-3          | Remote Monitoring (e.g., live feed)              | UC12                     |
| REQ-4          | Scheduled Cleaning                               | UC13                     |
| REQ-5          | Set Virtual Boundaries                           | UC7                      |
| REQ-6          | Cleaning Reports                                 | UC14                     |
| REQ-7          | Integration with Home Security                   | UC4, UC6                 |


## 6. Sketch of a sequence diegram

*Indicate dynamic behavior by event walk-through that indicates what your UC-demonstrator will do when events are generated by initiating actors*

## 6. Class diagram with Methods

*Annotate methods to your class dagram*

### References


## Appendix A (SE Glossary)

| Term | Acronym | Description |  
| --- | --- | --- |
| Concept | - | Prototypic class in domain model without methods |
| Boundary Concept | - | receives or sends events from or to external actors |
| Knowing Concepts | - | Data classes |
| Association | - | to be done ... |
| Domain Model | DM | Based on Responsibilities, concepts are defined (boundary or internal) with associations and properies |

## Appendix B (AI support)

*If you used AI tools, give a Keyword and section of this document, then a list of your inputs to the AI prompt*