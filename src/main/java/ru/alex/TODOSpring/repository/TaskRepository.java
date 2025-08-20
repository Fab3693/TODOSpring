package ru.alex.TODOSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.TODOSpring.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    long deleteById(int id);
}

