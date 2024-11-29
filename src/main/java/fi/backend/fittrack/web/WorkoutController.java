package fi.backend.fittrack.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fi.backend.fittrack.domain.Exercise;
import fi.backend.fittrack.domain.ExerciseRepository;
import fi.backend.fittrack.domain.Workout;
import fi.backend.fittrack.domain.WorkoutRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;
    
    @Autowired
    private ExerciseRepository exerciseRepository;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/list")
    public String listWorkouts(Model model) {
        model.addAttribute("workouts", workoutRepository.findAll());
        return "workouts/list";
    }

    @GetMapping("/add")
    public String showAddWorkoutForm(Model model) {
        Workout workout = new Workout();
        workout.setExercises(new ArrayList<>());
        model.addAttribute("workout", workout);
        return "workouts/add";
    }

    @PostMapping("/save")
    public String saveWorkout(@ModelAttribute Workout workout) {
        for (Exercise exercise : workout.getExercises()) {
            exercise.setWorkout(workout);
        }
        workoutRepository.save(workout);
        return "redirect:/list";
    }


    @GetMapping("/workouts/{id}")
    public String viewWorkoutDetails(@PathVariable ("id") Long id, Model model) {
        Workout workout = workoutRepository.findById(id).orElse(null);

        if (workout == null) {
            return "redirect:/list";
        }

        model.addAttribute("workout", workout);
        model.addAttribute("exercises", workout.getExercises());

        return "workouts/details";
    }
    
    @GetMapping("/workouts/delete/{id}")
    public String deleteWorkout(@PathVariable ("id") Long id) {
        workoutRepository.deleteById(id);
        return "redirect:/list";
    }

    @PostMapping("/workouts/mark-completed/{id}")
    public String markWorkoutCompleted(@PathVariable("id") Long workoutId) {
        Optional<Workout> workoutOpt = workoutRepository.findById(workoutId);
        if (workoutOpt.isPresent()) {
            Workout workout = workoutOpt.get();
            workout.setCompleted(true);
            workoutRepository.save(workout);
        }
        return "redirect:/list";
    }
     
}

