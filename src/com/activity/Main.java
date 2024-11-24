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
                    searchActivities();
                    break;
                case 4:
                    removeActivity();
                    break;
                case 5:
                    updateActivity();
                    break;
                case 6:
                    System.out.println("Goodbye!");
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
        System.out.println("3. Search Activities");
        System.out.println("4. Remove Activity");
        System.out.println("5. Update Activity");
        System.out.println("6. Exit");
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

    private static void searchActivities() {
        System.out.println("\n=== Search Activities ===");
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

    private static void updateActivity() {
        System.out.println("\n=== Update Activity ===");
        
        // First, find the activity to update
        String title = getStringInput("Enter activity title to update: ");
        Activity activity = manager.findActivityByTitle(title);
        
        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }

        // Display current activity details
        System.out.println("\nCurrent Activity Details:");
        displayActivityDetails(activity);

        // Create a new activity of the same type with updated details
        Activity updatedActivity = null;
        try {
            if (activity instanceof Meeting) {
                updatedActivity = updateMeeting((Meeting) activity);
            } else if (activity instanceof SeminarWorkshop) {
                updatedActivity = updateSeminarWorkshop((SeminarWorkshop) activity);
            } else if (activity instanceof Webinar) {
                updatedActivity = updateWebinar((Webinar) activity);
            } else if (activity instanceof Training) {
                updatedActivity = updateTraining((Training) activity);
            } else if (activity instanceof CommunityOutreach) {
                updatedActivity = updateCommunityOutreach((CommunityOutreach) activity);
            } else if (activity instanceof Exhibit) {
                updatedActivity = updateExhibit((Exhibit) activity);
            }

            if (updatedActivity != null) {
                manager.updateActivity(activity, updatedActivity);
                System.out.println("Activity updated successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error updating activity: " + e.getMessage());
        }
    }

    private static Meeting updateMeeting(Meeting meeting) {
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        String title = getUpdatedString("Title", meeting.getTitle());
        String description = getUpdatedString("Description", meeting.getDescription());
        LocalDateTime startDateTime = getUpdatedDateTime("Start Date and Time (yyyy-MM-dd HH:mm)", meeting.getStartDateTime());
        LocalDateTime endDateTime = getUpdatedDateTime("End Date and Time (yyyy-MM-dd HH:mm)", meeting.getEndDateTime());
        String venue = getUpdatedString("Venue", meeting.getVenue());
        String organizer = getUpdatedString("Organizer", meeting.getOrganizer());
        int maxParticipants = getUpdatedInt("Maximum Participants", meeting.getMaxParticipants());
        String agenda = getUpdatedString("Agenda", meeting.getAgenda());
        boolean isVirtual = getUpdatedBoolean("Is Virtual", meeting.isVirtual());

        return new Meeting(title, description, startDateTime, endDateTime,
                         venue, organizer, maxParticipants, agenda, isVirtual);
    }

    private static SeminarWorkshop updateSeminarWorkshop(SeminarWorkshop seminar) {
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        String title = getUpdatedString("Title", seminar.getTitle());
        String description = getUpdatedString("Description", seminar.getDescription());
        LocalDateTime startDateTime = getUpdatedDateTime("Start Date and Time (yyyy-MM-dd HH:mm)", seminar.getStartDateTime());
        LocalDateTime endDateTime = getUpdatedDateTime("End Date and Time (yyyy-MM-dd HH:mm)", seminar.getEndDateTime());
        String venue = getUpdatedString("Venue", seminar.getVenue());
        String organizer = getUpdatedString("Organizer", seminar.getOrganizer());
        int maxParticipants = getUpdatedInt("Maximum Participants", seminar.getMaxParticipants());
        
        System.out.println("Enter speakers (comma-separated): ");
        String[] speakers = getStringInput("").split(",");
        
        System.out.println("Enter workshop materials (comma-separated): ");
        String[] materials = getStringInput("").split(",");
        
        double registrationFee = getUpdatedDouble("Registration Fee", seminar.getRegistrationFee());

        return new SeminarWorkshop(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, speakers,
            materials, registrationFee
        );
    }

    private static Webinar updateWebinar(Webinar webinar) {
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        String title = getUpdatedString("Title", webinar.getTitle());
        String description = getUpdatedString("Description", webinar.getDescription());
        LocalDateTime startDateTime = getUpdatedDateTime("Start Date and Time (yyyy-MM-dd HH:mm)", webinar.getStartDateTime());
        LocalDateTime endDateTime = getUpdatedDateTime("End Date and Time (yyyy-MM-dd HH:mm)", webinar.getEndDateTime());
        String venue = getUpdatedString("Platform", webinar.getVenue());
        String organizer = getUpdatedString("Organizer", webinar.getOrganizer());
        int maxParticipants = getUpdatedInt("Maximum Participants", webinar.getMaxParticipants());
        String platformLink = getUpdatedString("Platform Link", webinar.getPlatformLink());
        
        System.out.println("Enter technical requirements (comma-separated): ");
        String[] requirements = getStringInput("").split(",");
        
        boolean recordingAvailable = getUpdatedBoolean("Recording Available", webinar.isRecordingAvailable());

        return new Webinar(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, platformLink,
            requirements, recordingAvailable
        );
    }

    private static Training updateTraining(Training training) {
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        String title = getUpdatedString("Title", training.getTitle());
        String description = getUpdatedString("Description", training.getDescription());
        LocalDateTime startDateTime = getUpdatedDateTime("Start Date and Time (yyyy-MM-dd HH:mm)", training.getStartDateTime());
        LocalDateTime endDateTime = getUpdatedDateTime("End Date and Time (yyyy-MM-dd HH:mm)", training.getEndDateTime());
        String venue = getUpdatedString("Venue", training.getVenue());
        String organizer = getUpdatedString("Organizer", training.getOrganizer());
        int maxParticipants = getUpdatedInt("Maximum Participants", training.getMaxParticipants());
        
        System.out.println("Enter trainers (comma-separated): ");
        String[] trainers = getStringInput("").split(",");
        
        System.out.println("Enter prerequisites (comma-separated): ");
        String[] prerequisites = getStringInput("").split(",");
        
        System.out.println("Enter learning objectives (comma-separated): ");
        String[] objectives = getStringInput("").split(",");
        
        boolean certificationOffered = getUpdatedBoolean("Certification Offered", training.isCertificationOffered());

        return new Training(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, trainers,
            prerequisites, objectives, certificationOffered
        );
    }

    private static CommunityOutreach updateCommunityOutreach(CommunityOutreach outreach) {
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        String title = getUpdatedString("Title", outreach.getTitle());
        String description = getUpdatedString("Description", outreach.getDescription());
        LocalDateTime startDateTime = getUpdatedDateTime("Start Date and Time (yyyy-MM-dd HH:mm)", outreach.getStartDateTime());
        LocalDateTime endDateTime = getUpdatedDateTime("End Date and Time (yyyy-MM-dd HH:mm)", outreach.getEndDateTime());
        String venue = getUpdatedString("Venue", outreach.getVenue());
        String organizer = getUpdatedString("Organizer", outreach.getOrganizer());
        int maxParticipants = getUpdatedInt("Maximum Participants", outreach.getMaxParticipants());
        String targetCommunity = getUpdatedString("Target Community", outreach.getTargetCommunity());
        
        System.out.println("Enter services (comma-separated): ");
        String[] services = getStringInput("").split(",");
        
        System.out.println("Enter required resources (comma-separated): ");
        String[] resources = getStringInput("").split(",");
        
        System.out.println("Enter partners (comma-separated): ");
        String[] partners = getStringInput("").split(",");

        return new CommunityOutreach(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, targetCommunity,
            services, resources, partners
        );
    }

    private static Exhibit updateExhibit(Exhibit exhibit) {
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        String title = getUpdatedString("Title", exhibit.getTitle());
        String description = getUpdatedString("Description", exhibit.getDescription());
        LocalDateTime startDateTime = getUpdatedDateTime("Start Date and Time (yyyy-MM-dd HH:mm)", exhibit.getStartDateTime());
        LocalDateTime endDateTime = getUpdatedDateTime("End Date and Time (yyyy-MM-dd HH:mm)", exhibit.getEndDateTime());
        String venue = getUpdatedString("Venue", exhibit.getVenue());
        String organizer = getUpdatedString("Organizer", exhibit.getOrganizer());
        int maxParticipants = getUpdatedInt("Maximum Participants", exhibit.getMaxParticipants());
        String theme = getUpdatedString("Theme", exhibit.getTheme());
        
        System.out.println("Enter exhibitors (comma-separated): ");
        String[] exhibitors = getStringInput("").split(",");
        
        System.out.println("Enter exhibits (comma-separated): ");
        String[] exhibits = getStringInput("").split(",");
        
        boolean isInteractive = getUpdatedBoolean("Interactive Exhibit", exhibit.isInteractive());
        double entryFee = getUpdatedDouble("Entry Fee", exhibit.getEntryFee());

        return new Exhibit(
            title, description, startDateTime, endDateTime,
            venue, organizer, maxParticipants, theme,
            exhibitors, exhibits, isInteractive, entryFee
        );
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

    private static String getUpdatedString(String prompt, String currentValue) {
        System.out.printf("%s [%s]: ", prompt, currentValue);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? currentValue : input;
    }

    private static int getUpdatedInt(String prompt, int currentValue) {
        while (true) {
            System.out.printf("%s [%d]: ", prompt, currentValue);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return currentValue;
            }
            try {
                int value = Integer.parseInt(input);
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

    private static LocalDateTime getUpdatedDateTime(String prompt, LocalDateTime currentValue) {
        while (true) {
            System.out.printf("%s [%s]: ", prompt, currentValue.format(dateFormatter));
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return currentValue;
            }
            try {
                return LocalDateTime.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter date and time in format: yyyy-MM-dd HH:mm");
            }
        }
    }

    private static boolean getUpdatedBoolean(String prompt, boolean currentValue) {
        while (true) {
            System.out.printf("%s [%s]: ", prompt, currentValue);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return currentValue;
            }
            if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Please enter true or false.");
        }
    }

    private static double getUpdatedDouble(String prompt, double currentValue) {
        while (true) {
            System.out.printf("%s [%.2f]: ", prompt, currentValue);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return currentValue;
            }
            try {
                double value = Double.parseDouble(input);
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
