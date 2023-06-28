package com.example.taskmanagerdemo.service;

import com.example.taskmanagerdemo.model.Taska;
import com.example.taskmanagerdemo.model.User;
import com.example.taskmanagerdemo.repository.TaskaRepository;
import com.example.taskmanagerdemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskaService {

    private final TaskaRepository taskaRepository;

    private final UserRepository userRepository;

    public TaskaService(TaskaRepository taskaRepository, UserRepository userRepository) {
        this.taskaRepository = taskaRepository;
        this.userRepository = userRepository;
    }

    public Taska addTaska(Taska taska, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            taska.setUser(optionalUser.get());
            Taska save = taskaRepository.save(taska);
            return save;
        } else {
            return null;
        }
    }

    public Taska updateTaska(Long id, Taska taska) {
        Taska taska2 = null;
        Optional<Taska> optionalTaska = taskaRepository.findById(id);

        if (optionalTaska.isPresent()) {
            taska2 = optionalTaska.get();
            taska2.setName(taska.getName());
            return taskaRepository.save(taska2);
        } else {
            return null;
        }
    }


    public List<Taska> getAllTaskas() {
        return taskaRepository.findAll();
    }

    public Taska getTaskaById(Long id) {
        Optional<Taska> optionalTaska = taskaRepository.findById(id);
         if(optionalTaska.isPresent()) {
             return optionalTaska.get();
         } else {
             return null;
         }
    }

//    public Taska createTaska(Taska taska) {
//        return taskaRepository.save(taska);
//    }

 //   public Taska updateTaska(Long id, Taska taska) {
//        Taska existingTaska = taskaRepository.findById(id).orElse(null);
//        if (existingTaska != null) {
//            existingTaska.setName(taska.getName());
//            existingTaska.setUser(taska.getUser());
//            return taskaRepository.save(existingTaska);
//        }
//        return null;
//    }

    public void deleteTaska(Long id) {
        taskaRepository.deleteById(id);
    }
}
