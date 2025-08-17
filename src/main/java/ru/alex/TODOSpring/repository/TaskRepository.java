package ru.alex.TODOSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.TODOSpring.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    void deleteByTitle(String title);
    Task findByTitle(String title);
}
