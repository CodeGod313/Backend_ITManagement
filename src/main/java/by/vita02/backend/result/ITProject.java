package by.vita02.backend.result;

import by.vita02.backend.enums.ProjectType;

public class ITProject {
    private ProjectType projectType;
    private int costOfConventionalUnit;
    private String nameOfConventionalUnit;

    public ITProject(ProjectType projectType, int costOfConventionalUnit, String nameOfConventionalUnit) {
        this.projectType = projectType;
        this.costOfConventionalUnit = costOfConventionalUnit;
        this.nameOfConventionalUnit = nameOfConventionalUnit;
    }
}