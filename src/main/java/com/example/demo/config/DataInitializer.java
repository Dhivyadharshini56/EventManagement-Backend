package com.example.demo.config;

import com.example.demo.model.Event;
import com.example.demo.model.Notification;
import com.example.demo.model.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final NotificationRepository notificationRepository;

    public DataInitializer(UserRepository userRepository,
                           EventRepository eventRepository,
                           NotificationRepository notificationRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedEvents();
        seedNotifications();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            List<User> defaultUsers = Arrays.asList(
                    new User("1", "Admin", "admin@events.com", "Administrator", "password"),
                    new User("2", "John Organizer", "organizer@events.com", "Event Organizer", "password"),
                    new User("3", "Jane User", "user@events.com", "Registered User", "password")
            );
            userRepository.saveAll(defaultUsers);
            System.out.println("Default users seeded successfully.");
        }
    }

    private void seedEvents() {
        if (eventRepository.count() == 0) {
            List<Event> defaultEvents = Arrays.asList(
                    new Event("1", "Tech Summit", "2026-06-15T09:00:00", "Convention Center, NY",
                            "A global summit discussing the latest in tech innovations and AI. Network with industry leaders and discover the future of technology.",
                            500, "Technology", "2", 150.0,
                            "https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=800&q=80"),

                    new Event("2", "Music Fest", "2026-07-20T17:00:00", "Central Park",
                            "An open-air music festival featuring top artists from across the globe. Grab your friends and enjoy a weekend of unforgettable melodies.",
                            2000, "Entertainment", "2", 50.0,
                            "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?w=800&q=80"),

                    new Event("3", "Startup Conf", "2026-08-10T10:00:00", "Silicon Valley Expo Area",
                            "Connect with emerging startups, renowned angel investors, and venture capitalists. Pitch your ideas and learn how to scale your business.",
                            350, "Business", "2", 299.0,
                            "https://images.unsplash.com/photo-1556761175-5973dc0f32d7?w=800&q=80"),

                    new Event("4", "Photo Class", "2026-09-05T14:00:00", "Downtown Art Studio",
                            "A comprehensive workshop on modern digital photography, lighting techniques, and post-processing with industry experts.",
                            50, "Workshop", "2", 85.0,
                            "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=800&q=80"),

                    new Event("5", "Yoga Retreat", "2026-05-12T07:00:00", "Zen Gardens Resort",
                            "A 3-day retreat focused on mindfulness, yoga, meditation, and healthy eating, designed to reset your mind and body.",
                            100, "Health", "2", 450.0,
                            "https://images.unsplash.com/photo-1506126613408-eca07ce68773?w=800&q=80"),

                    new Event("6", "Gaming Tourney", "2026-11-20T18:00:00", "Arena Complex",
                            "Watch top professional gaming teams compete live on the big stage for the regional championship trophy and cash prizes.",
                            3000, "Gaming", "2", 35.0,
                            "https://images.unsplash.com/photo-1542751371-adc38448a05e?w=800&q=80"),

                    new Event("7", "Art Gallery", "2026-10-01T10:00:00", "City Modern Art Museum",
                            "An exclusive viewing of contemporary masterpieces from rising global artists. Includes a guided tour and wine tasting.",
                            200, "Art", "2", 75.0,
                            "https://images.unsplash.com/photo-1460661419201-fd4cecdf8a8b?w=800&q=80"),

                    new Event("8", "Wine Dinner", "2026-12-05T19:30:00", "The Grand Hotel Plaza",
                            "Savor an exquisite multi-course dinner prepared by award-winning chefs, paired perfectly with premium vintage wines.",
                            80, "Food & Drink", "2", 250.0,
                            "https://images.unsplash.com/photo-1555244162-803834f70033?w=800&q=80"),

                    new Event("9", "Food Expo", "2026-09-10T11:00:00", "City Square Market",
                            "Explore thousands of unique foods and delicacies from over 50 countries, cooked by world-renowned street chefs.",
                            5000, "Food & Drink", "2", 25.0,
                            "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=800&q=80"),

                    new Event("10", "Leadership Seminar", "2026-10-15T09:30:00", "Business Innovation Hub",
                            "An interactive seminar focusing on leadership strategies in the post-AI era, led by Fortune 500 executives.",
                            400, "Seminar", "2", 199.0,
                            "https://images.unsplash.com/photo-1587825140708-dfaf72ae4b04?w=800&q=80"),

                    new Event("11", "Wedding Fair", "2026-11-05T10:00:00", "Royal Palace Grounds",
                            "The ultimate wedding exhibition featuring premium designers, decorators, and planners to help organize your dream wedding.",
                            1500, "Wedding", "2", 45.0,
                            "https://images.unsplash.com/photo-1519225421980-715cb0215aed?w=800&q=80"),

                    new Event("12", "Cultural Fest", "2026-08-20T16:00:00", "Heritage Park",
                            "A vibrant celebration of global cultures through dance, music, traditional crafts, and immersive storytelling.",
                            2500, "Cultural", "2", 15.0,
                            "https://images.unsplash.com/photo-1533105079780-92b9be482077?w=800&q=80"),

                    new Event("13", "Art Auction", "2026-12-12T18:00:00", "Downtown Art Gallery",
                            "Bid on exclusive original paintings, sculptures, and digital art pieces at this high-end art auction event.",
                            150, "Art", "2", 500.0,
                            "https://images.unsplash.com/photo-1549490349-8643362247b5?w=800&q=80"),

                    new Event("14", "Award Show", "2026-12-28T19:00:00", "The Platinum Theater",
                            "Join us for a prestigious evening honoring the greatest achievements across multiple industries over the past year.",
                            1000, "Award Ceremonies", "2", 120.0,
                            "https://images.unsplash.com/photo-1511516104886-f14d8ec6bb3e?w=800&q=80"),

                    new Event("15", "Comedy Night", "2026-10-20T19:00:00", "College Auditorium",
                            "An evening of non-stop laughs with top stand-up comedians and witty improv acts. Perfect weekend entertainment for everyone!",
                            120, "Comedy", "2", 800.0,
                            "https://images.unsplash.com/photo-1585699324551-f6c309eedeca?w=800&q=80"),

                    new Event("16", "Inter-College Cricket Cup", "2026-11-14T08:30:00", "University Sports Complex",
                            "Annual championship tournament featuring the best collegiate cricket teams battling for the championship trophy.",
                            400, "Sports", "2", 200.0,
                            "https://images.unsplash.com/photo-1531415074868-036b107e775a?w=800&q=80"),

                    new Event("17", "National Hackathon 2026", "2026-10-25T09:00:00", "Innovation Lab & Tech Center",
                            "A 36-hour adrenaline-filled hackathon bringing together programmers, designers, and innovators to build groundbreaking tech solutions.",
                            250, "Hackathon", "2", 150.0,
                            "https://images.unsplash.com/photo-1504384308090-c894fdcc538d?w=800&q=80"),

                    new Event("18", "Live Music & Rock Night", "2026-11-28T18:00:00", "Open Air Amphitheatre",
                            "Experience electrifying acoustic sets, rock bands, and indie music sensations under the starry night sky.",
                            600, "Music", "2", 400.0,
                            "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?w=800&q=80"),

                    new Event("19", "Mega Dance Battle", "2026-12-04T17:00:00", "Grand Performing Arts Hall",
                            "High-octane national dance competition with solo and crew performances across hip-hop, contemporary, and classical styles.",
                            500, "Dance", "2", 300.0,
                            "https://images.unsplash.com/photo-1547153760-18fc86324498?w=800&q=80"),

                    new Event("20", "Annual College Fest", "2026-12-18T10:00:00", "Main Campus Grounds",
                            "The biggest campus festival of the year! Enjoy live performances, food stalls, gaming arenas, tech expos, and art galleries.",
                            1500, "College Events", "2", 100.0,
                            "https://images.unsplash.com/photo-1492684223066-81342ee5ff30?w=800&q=80")
            );
            eventRepository.saveAll(defaultEvents);
            System.out.println("Default events seeded successfully.");
        } else {
            // Clean up any legacy "Food" category events
            List<Event> existingEvents = eventRepository.findAll();
            for (Event evt : existingEvents) {
                if ("Food".equalsIgnoreCase(evt.getCategory())) {
                    evt.setCategory("Food & Drink");
                    eventRepository.save(evt);
                }
            }
        }
    }

    private void seedNotifications() {
        if (notificationRepository.count() == 0) {
            Notification welcome = new Notification("1", "Welcome to the new Event Management System!",
                    Instant.now().toString(), "System", null);
            notificationRepository.save(welcome);
            System.out.println("Default notifications seeded successfully.");
        }
    }
}
