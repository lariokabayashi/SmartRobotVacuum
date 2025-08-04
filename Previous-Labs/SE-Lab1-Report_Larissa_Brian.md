
# Software Engineering (SE) Lab 1 Report - Team 13b

### Authors

| Family name | First name(s) | *Partner A or B* |  
| --- | --- | --- |
| Okabayashi | Larissa | Partner A |  
| Im | Brian | Partner B |

### Version 
Final

*Partner A will consider the Robot App in more detail, partner B the robot firmware*

## Glossary

| Term | Acronym | Description |  
| --- | --- | --- |
| Robot Mower | RM | Robotic Lawn Mower |
| Robot Vacuum | RV | Robotic Vacuum Cleaner |

## 1. Market Analysis for Robotic Vacuum Cleaner

To conduct a comprehensive market analysis and identify typical product features for robotic vacuum cleaners and their associated apps, I focused on current market dynamics, leading technologies, and consumer-oriented trends. The global market has seen significant growth, reaching around $9–9.4 billion in 2024 and projected to expand to $31.8 billion by 2033, driven by rising urbanization, increased disposable income, and the growing adoption of smart home devices [1], [2], [3].

I analyzed key players and regional performance, identifying Asia Pacific as the dominant market, with top brands like iRobot, Ecovacs, and Roborock holding 50–65% of the global share. Emerging competitors are also gaining traction by offering features such as AI-powered navigation and self-emptying capabilities [4].

For the robotic vacuum cleaner system, I identified core features like advanced lidar or SLAM-based navigation, multi-sensor integration, real-time mapping, and efficient battery and dustbin systems. On the app side, essential capabilities include multi-floor mapping, scheduling, zone-based cleaning, real-time monitoring, voice assistant support, and user-friendly controls. These features reflect broader consumer expectations for automation, customization, and seamless connectivity.

The detailed comparative analysis between brands such as bObsweep and Marstak, and the identification of hardware and software features, is documented in Appendix A.

## 2. List of Features and Requirements

*From the appendix, extract a list of features that are requirements relevant for the software. Add a column that indicates relevance for the app (i.e. mobil app), the firmware (i.e. robot software) or for both!*

| Feature | Description | Relevance |
|--------|-------------|-----------|
| Bot Control | Start/stop, send to charge/self-empty | App |
| Mapping | Save floor maps, auto room division, multi-level mapping | Both |
| Scheduling | Custom scheduling by room and time | App |
| Cleaning Preferences | Adjust suction power, cleaning behavior per room, quiet mode | Both |
| History Logs | Detailed cleaning logs, real-time monitoring, live tracking | App |
| Zone/No-Go Zones | Set cleaning/no-cleaning zones | Both |
| Smart Control | App control, onboard buttons, voice assistant integration | Both |
| Dark Mode | App visual customization | App |
| Help Center | In-app support feature (coming soon) | App |
| Navigation | SLAM-based or lidar-based mapping, real-time navigation | Firmware |
| Boot/Mapping | Fast boot, auto room division after first clean | Firmware |
| Models | Variant features like self-emptying, obstacle avoidance | Firmware |
| Dustbin/Docking | Self-emptying bin, smart docking with auto disposal | Firmware |
| Suction/Filters | Adjustable suction, allergy-friendly filters | Firmware |
| Battery | Long runtime, quiet operation | Firmware |
| Efficiency Architecture | Single-chip systems, autonomous processing | Firmware |

## 3. Stakeholder List for Requirements Engineering Stage

- **End User**: Purchase the robot vacuum and utilises the features, ranging in prior knowledge of functionality.
- **Manufacturer**: Produce the physical product of the robot vacuum along with the firmware that the vacuum runs on.
- **Software Developer**: Develops the Robot vacuum App for the end users to interact with when using the product wirelessly. Additionally, implements the more complex features and controls available.
- **Customer Support**: Resolves issues which arise from end users from their use of the vacuum.
- **Marketing**: Creates exposure for the product, displaying features of the vacuum to promote purchases.
- **Vendors**: Sell the product at stores and online to consumers, purchasing the product at mass from the manufacturer.
- **Carpet Care Businesses**: Have knowledge on the best practices and features for the vacuum to have for the best care. Can endorse the vacuum to consumers.

## 4. Kano Classification

| Feature | Kano Classification |
|--------|-------------|
| Bot Control | Must-be |
| Mapping | One-dimensional |
| Scheduling | Must-be |
| Cleaning Preferences | Attractive |
| History Logs | Attractive |
| Zone/No-Go Zones | Attractive |
| Smart Control | Attractive |
| Dark Mode | Indifferent |
| Help Center | Attractive |
| Navigation | Must-be |
| Boot/Mapping | Attractive |
| Models | Attractive |
| Dustbin/Docking | Attractive |
| Suction/Filters | Attractive |
| Battery | One-dimensional |
| Efficiency Architecture | Indifferent |

## 5. App User Stories

| Identifier | User Story |
|------------|------------|
| UST-1 | As a user, I will be able to start, stop, and pause the vacuum cleaner via the app. |
| UST-2 | As a user, I will be able to schedule cleaning sessions for specific times and days. |
| UST-3 | As a user, I will be able to view a live map of the cleaning progress in real-time. |
| UST-4 | As a user, I will be able to define and manage no-go zones and specific cleaning areas through the app. |
| UST-5 | As a user, I will be able to receive notifications when the cleaning is complete or if the robot encounters issues. |
| UST-6 | As a user, I will be able to view the robot’s cleaning history and performance over time. |
| UST-7 | As a user, I will be able to connect the app to voice assistants such as Alexa or Google Assistant. |
| UST-8 | As a user, I will be able to monitor battery status and charging progress through the app. |
| UST-9 | As a user, I will be able to remotely send the robot back to its charging dock. |
| UST-10 | As a user, I will be able to receive alerts when the dustbin is full or the filter needs replacement. |
| UST-11 | As a user, I will be able to update the robot’s firmware through the app when updates are available. |
| UST-12 | As a user, I will be able to switch between cleaning modes (e.g., quiet, turbo, auto) via the app. |
| UST-13 | As a user, I will be able to manage multiple devices and assign names to each vacuum cleaner. |
| UST-14 | As a user, I will be able to share device access with family members using the app. |

## 6. Firmware User Stories

| Identifier | User Story |
|------------|------------|
| UST-15 | As a user, I will be able to start, stop, and pause the vacuum cleaner through physical buttons on the robot. |
| UST-16 | As a user, I want the robot vacuum to safely return to the dock when the battery is low, so it can recharge autonomously. |
| UST-17 | As a user, I want the robot vacuum to remember where it left off after recharging so it can resume cleaning from the same spot. |
| UST-18 | As a user, I want the firmware to reliably store cleaning maps and store it locally for more efficient future cleaning paths. |
| UST-19 | As a user, I want the robot vacuum to automatically adjust suction power based on floor type to optimize performance and battery life. |
| UST-20 | As a user, I want the robot vacuum to stop cleaning immediately if it detects that it is stuck or entangled. |
| UST-21 | As a user, I want the firmware to prevent unsafe operation to ensure safety. |
| UST-22 | As a user, I want the firmware to autonomously determine room divisions in the software. |
| UST-23 | As a user, I will be able to control the robot vacuum through voice commands such as start, stop and pause. |
| UST-24 | As a user, I want the robot vacuum to navigate efficiently around obstacles and furniture while mapping the area, so it cleans the entire room systematically. |
| UST-25 | As a user, I want the robot vacuum to automatically empty its dustbin into a home station when full, so I don’t have to manually clean the robot's storage. |
| UST-26 | As a user, I want the robot vacuum to avoid obstacles like furniture, walls, or pets, so it doesn’t bump into things or get stuck during cleaning. |
| UST-27 | As a user, I want the robot vacuum to boot up and be ready to clean within seconds of being powered on, so I don’t have to wait for long startup times. |
| UST-28 | As a user, I want the robot vacuum to lower sounds during specific hours based on firmware settings. |

## Appendix A

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

---

### References

[1] Globenewswire, "Robotic Vacuum Cleaners Market Investment Opportunities and Strategy Outlook 2025–2033," Feb. 2025. [Online]. Available: https://www.globenewswire.com/news-release/2025/02/26/3032718/28124/en/Robotic-Vacuum-Cleaners-Market-Investment-Opportunities-and-Strategy-Outlook-2025-2033-Revenues-Worldwide-to-Cross-31-7-Billion-by-2033.html  
[2] The Business Research Company, "Robotic Vacuum Cleaners Global Market Report." [Online]. Available: https://www.thebusinessresearchcompany.com/report/robotic-vacuum-cleaners-global-market-report
[3] Research and Markets, "Robotic Vacuum Cleaners Market Report." [Online]. Available: https://www.researchandmarkets.com/reports/5735435/robotic-vacuum-cleaners-market-report  
[4] Future Market Insights, "Robotic Vacuum Cleaners Market Share Analysis." [Online]. Available: https://www.futuremarketinsights.com/reports/robotic-vacuum-cleaners-market-share-analysis  
[5] bObsweep, "bObsweep Models." [Online]. Available: https://www.bobsweep.com/models  
[6] Mordor Intelligence, "Robot Vacuum Cleaners Market." [Online]. Available: https://www.mordorintelligence.com/industry-reports/robot-vacuum-cleaners-market  
[7] bObsweep, "Compare Models." [Online]. Available: https://www.bobsweep.com/compare  
