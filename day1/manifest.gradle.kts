fun runCommand(command: String, workingDir: File): String {
    return ProcessBuilder(*command.split(" ").toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()
        .inputStream
        .bufferedReader()
        .use { it.readText().trim() }
}

val gitCommitId = runCommand("git rev-parse --short HEAD", project.rootDir)
val gitCommitCount = runCommand("git rev-list --count HEAD", project.rootDir).toInt()

extra["targetSdk"] = 34 // 14.0
extra["minSdk"] = 21    // 5.0

extra["versionCode"] = gitCommitCount
extra["versionName"] = "git.$gitCommitCount.$gitCommitId"