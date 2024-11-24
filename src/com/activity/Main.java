package com.activity;

import com.activity.impl.Meeting;
import com.activity.impl.SeminarWorkshop;
import com.activity.impl.Webinar;
import com.activity.impl.Training;
import com.activity.impl.CommunityOutreach;
import com.activity.impl.Exhibit;
import com.activity.interfaces.Activity;
import com.activity.iterator.ActivityIterator;
import com.activity.manager.ActivityManager;
import com.activity.observer.ActivityLogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ActivityManager manager;
    private static Scanner scanner;
    private static DateTimeFormatter dateFormatter;

    public static void main(String[] args) {
        manager = new ActivityManager();
        scanner = new Scanner(System.in);
        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        // Add activity logger observer
        manager.addObserver(new ActivityLogger());

        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addActivity();
                    break;
                case 2:
                    viewAllActivities();
                    break;
                case 3:
                    searchActivity();
                    break;
                case 4:
                    removeActivity();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Activity Management System ===");
        System.out.println("1. Add New Activity");
        System.out.println("2. View All Activities");
        System.out.println("3. Search Activity");
        System.out.println("4. Remove Activity");
        System.out.println("5. Exit");
    }

    private static void addActivity() {
        System.out.println("\n=== Add New Activity ===");
        System.out.println("Select activity type:");
        System.out.println("1. Meeting");
        System.out.println("2. Seminar-Workshop");
        System.out.println("3. Webinar");
        System.out.println("4. Training");
        System.out.println("5. Community Outreach");
        System.out.println("6. Exhibit");
        
        int type = getIntInput("Enter activity type number: ");
        
        switch (type) {
            case 1:
                addMeeting();
                break;
            case 2:
                addSeminarWorkshop();
                break;
            case 3:
                addWebinar();
                break;
            case 4:
                addTraining();
                break;
            case 5:
                addCommunityOutreach();
                break;
            case 6:
                addExhibit();
                break;
            default:
                System.out.println("Invalid activity type.");
        }
    }

    private static void addMeeting() {
        System.out.println("\nEnter Meeting Details:");
        
        String title = getStringInput("Title: ");
        String description = getStringInput("Description: ");
        LocalDateTime startDateTime = getDateTimeInput("Start Date and Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime endDateTime = getDateTimeInput("End Date and Time (yyyy-MM-dd HH:mm): ");
        String venue = getStringInput("Venue: ");
        String organizer = getStringInput("Organizer: ");
        int maxParticipants = getIntInput("Maximum Participants: ");
        String agenda = getStringInput("Agenda: ");
        boolean isVirtual = getBooleanInput("Is Virtual Meeting? (true/false): ");

        Meeting meeting = new Meeting(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, agenda, isVirtual
        );

        manager.addActivity(meeting);
        System.out.println("Meeting added successfully!");
    }

    private static void addSeminarWorkshop() {
        System.out.println("\nEnter Seminar-Workshop Details:");
        
        String title = getStringInput("Title: ");
        String description = getStringInput("Description: ");
        LocalDateTime startDateTime = getDateTimeInput("Start Date and Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime endDateTime = getDateTimeInput("End Date and Time (yyyy-MM-dd HH:mm): ");
        String venue = getStringInput("Venue: ");
        String organizer = getStringInput("Organizer: ");
        int maxParticipants = getIntInput("Maximum Participants: ");
        
        System.out.println("Enter speakers (comma-separated): ");
        String[] speakers = getStringInput("").split(",");
        
        System.out.println("Enter workshop materials (comma-separated): ");
        String[] materials = getStringInput("").split(",");
        
        double registrationFee = getDoubleInput("Registration Fee: ");

        SeminarWorkshop seminar = new SeminarWorkshop(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, speakers,
            materials, registrationFee
        );

        manager.addActivity(seminar);
        System.out.println("Seminar-Workshop added successfully!");
    }

    private static void addWebinar() {
        System.out.println("\nEnter Webinar Details:");
        
        String title = getStringInput("Title: ");
        String description = getStringInput("Description: ");
        LocalDateTime startDateTime = getDateTimeInput("Start Date and Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime endDateTime = getDateTimeInput("End Date and Time (yyyy-MM-dd HH:mm): ");
        String venue = getStringInput("Platform: ");
        String organizer = getStringInput("Organizer: ");
        int maxParticipants = getIntInput("Maximum Participants: ");
        String platformLink = getStringInput("Platform Link: ");
        
        System.out.println("Enter technical requirements (comma-separated): ");
        String[] requirements = getStringInput("").split(",");
        
        boolean recordingAvailable = getBooleanInput("Recording Available (true/false): ");

        Webinar webinar = new Webinar(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, platformLink,
            requirements, recordingAvailable
        );

        manager.addActivity(webinar);
        System.out.println("Webinar added successfully!");
    }

    private static void addTraining() {
        System.out.println("\nEnter Training Details:");
        
        String title = getStringInput("Title: ");
        String description = getStringInput("Description: ");
        LocalDateTime startDateTime = getDateTimeInput("Start Date and Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime endDateTime = getDateTimeInput("End Date and Time (yyyy-MM-dd HH:mm): ");
        String venue = getStringInput("Venue: ");
        String organizer = getStringInput("Organizer: ");
        int maxParticipants = getIntInput("Maximum Participants: ");
        
        System.out.println("Enter trainers (comma-separated): ");
        String[] trainers = getStringInput("").split(",");
        
        System.out.println("Enter prerequisites (comma-separated): ");
        String[] prerequisites = getStringInput("").split(",");
        
        System.out.println("Enter learning objectives (comma-separated): ");
        String[] objectives = getStringInput("").split(",");
        
        boolean certificationOffered = getBooleanInput("Certification Offered (true/false): ");

        Training training = new Training(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, trainers,
            prerequisites, objectives, certificationOffered
        );

        manager.addActivity(training);
        System.out.println("Training added successfully!");
    }

    private static void addCommunityOutreach() {
        System.out.println("\nEnter Community Outreach Details:");
        
        String title = getStringInput("Title: ");
        String description = getStringInput("Description: ");
        LocalDateTime startDateTime = getDateTimeInput("Start Date and Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime endDateTime = getDateTimeInput("End Date and Time (yyyy-MM-dd HH:mm): ");
        String venue = getStringInput("Venue: ");
        String organizer = getStringInput("Organizer: ");
        int maxParticipants = getIntInput("Maximum Participants: ");
        String targetCommunity = getStringInput("Target Community: ");
        
        System.out.println("Enter services (comma-separated): ");
        String[] services = getStringInput("").split(",");
        
        System.out.println("Enter required resources (comma-separated): ");
        String[] resources = getStringInput("").split(",");
        
        System.out.println("Enter partners (comma-separated): ");
        String[] partners = getStringInput("").split(",");

        CommunityOutreach outreach = new CommunityOutreach(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, targetCommunity,
            services, resources, partners
        );

        manager.addActivity(outreach);
        System.out.println("Community Outreach added successfully!");
    }

    private static void addExhibit() {
        System.out.println("\nEnter Exhibit Details:");
        
        String title = getStringInput("Title: ");
        String description = getStringInput("Description: ");
        LocalDateTime startDateTime = getDateTimeInput("Start Date and Time (yyyy-MM-dd HH:mm): ");
        LocalDateTime endDateTime = getDateTimeInput("End Date and Time (yyyy-MM-dd HH:mm): ");
        String venue = getStringInput("Venue: ");
        String organizer = getStringInput("Organizer: ");
        int maxParticipants = getIntInput("Maximum Participants: ");
        String theme = getStringInput("Theme: ");
        
        System.out.println("Enter exhibitors (comma-separated): ");
        String[] exhibitors = getStringInput("").split(",");
        
        System.out.println("Enter exhibits (comma-separated): ");
        String[] exhibits = getStringInput("").split(",");
        
        boolean isInteractive = getBooleanInput("Interactive Exhibit (true/false): ");
        double entryFee = getDoubleInput("Entry Fee: ");

        Exhibit exhibit = new Exhibit(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, theme,
            exhibitors, exhibits, isInteractive, entryFee
        );

        manager.addActivity(exhibit);
        System.out.println("Exhibit added successfully!");
    }

    private static void viewAllActivities() {
        System.out.println("\n=== All Activities ===");
        ActivityIterator iterator = manager.getIterator();
        boolean hasActivities = false;
        
        while (iterator.hasNext()) {
            hasActivities = true;
            Activity activity = iterator.next();
            displayActivityDetails(activity);
        }
        
        if (!hasActivities) {
            System.out.println("No activities found.");
        }
    }

    private static void searchActivity() {
        System.out.println("\n=== Search Activity ===");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Type");
        
        int searchChoice = getIntInput("Enter search choice: ");
        
        switch (searchChoice) {
            case 1:
                String title = getStringInput("Enter activity title: ");
                Activity activity = manager.findActivityByTitle(title);
                if (activity != null) {
                    displayActivityDetails(activity);
                } else {
                    System.out.println("Activity not found.");
                }
                break;
                
            case 2:
                String type = getStringInput("Enter activity type (e.g., MEETING): ");
                List<Activity> activities = manager.findActivitiesByType(type);
                if (!activities.isEmpty()) {
                    for (Activity a : activities) {
                        displayActivityDetails(a);
                    }
                } else {
                    System.out.println("No activities found of type: " + type);
                }
                break;
                
            default:
                System.out.println("Invalid search choice.");
        }
    }

    private static void removeActivity() {
        String title = getStringInput("\nEnter title of activity to remove: ");
        Activity activity = manager.findActivityByTitle(title);
        
        if (activity != null) {
            manager.removeActivity(activity);
            System.out.println("Activity removed successfully!");
        } else {
            System.out.println("Activity not found.");
        }
    }

    private static void displayActivityDetails(Activity activity) {
        System.out.println("\n--- " + activity.getType() + " Details ---");
        System.out.println("Title: " + activity.getTitle());
        System.out.println("Description: " + activity.getDescription());
        System.out.println("Start: " + activity.getStartDateTime().format(dateFormatter));
        System.out.println("End: " + activity.getEndDateTime().format(dateFormatter));
        System.out.println("Venue: " + activity.getVenue());
        System.out.println("Organizer: " + activity.getOrganizer());
        System.out.println("Max Participants: " + activity.getMaxParticipants());

        if (activity instanceof Meeting) {
            Meeting meeting = (Meeting) activity;
            System.out.println("Agenda: " + meeting.getAgenda());
            System.out.println("Virtual: " + meeting.isVirtual());
        } else if (activity instanceof SeminarWorkshop) {
            SeminarWorkshop seminar = (SeminarWorkshop) activity;
            System.out.println("Speakers: " + String.join(", ", seminar.getSpeakers()));
            System.out.println("Materials: " + String.join(", ", seminar.getWorkshopMaterials()));
            System.out.println("Registration Fee: " + seminar.getRegistrationFee());
        } else if (activity instanceof Webinar) {
            Webinar webinar = (Webinar) activity;
            System.out.println("Platform Link: " + webinar.getPlatformLink());
            System.out.println("Technical Requirements: " + String.join(", ", webinar.getTechnicalRequirements()));
            System.out.println("Recording Available: " + webinar.isRecordingAvailable());
        } else if (activity instanceof Training) {
            Training training = (Training) activity;
            System.out.println("Trainers: " + String.join(", ", training.getTrainers()));
            System.out.println("Prerequisites: " + String.join(", ", training.getPrerequisites()));
            System.out.println("Learning Objectives: " + String.join(", ", training.getLearningObjectives()));
            System.out.println("Certification Offered: " + training.isCertificationOffered());
        } else if (activity instanceof CommunityOutreach) {
            CommunityOutreach outreach = (CommunityOutreach) activity;
            System.out.println("Target Community: " + outreach.getTargetCommunity());
            System.out.println("Services: " + String.join(", ", outreach.getServices()));
            System.out.println("Required Resources: " + String.join(", ", outreach.getRequiredResources()));
            System.out.println("Partners: " + String.join(", ", outreach.getPartners()));
        } else if (activity instanceof Exhibit) {
            Exhibit exhibit = (Exhibit) activity;
            System.out.println("Theme: " + exhibit.getTheme());
            System.out.println("Exhibitors: " + String.join(", ", exhibit.getExhibitors()));
            System.out.println("Exhibits: " + String.join(", ", exhibit.getExhibits()));
            System.out.println("Interactive: " + exhibit.isInteractive());
            System.out.println("Entry Fee: " + exhibit.getEntryFee());
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Please enter 'true' or 'false'.");
        }
    }

    private static LocalDateTime getDateTimeInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return LocalDateTime.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter date and time in format: yyyy-MM-dd HH:mm");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
