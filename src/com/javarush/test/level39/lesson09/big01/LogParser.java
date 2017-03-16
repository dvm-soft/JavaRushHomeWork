package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery
{
    private Path logDir;
    private List<LogItem> logList = new ArrayList<>();

    public LogParser(Path logDir)
    {
        this.logDir = logDir;
        loadLogsFromPath(logDir);
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before)
    {
        Set<String> iPs = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before))
                iPs.add(item.getIp());
        return iPs.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before)
    {
        Set<String> iPs = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before))
                iPs.add(item.getIp());
        return iPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before)
    {
        Set<String> iPs = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && user.equals(item.getUser()))
                iPs.add(item.getIp());
        return iPs;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before)
    {
        Set<String> iPs = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && event.equals(item.getEvent()))
                iPs.add(item.getIp());
        return iPs;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before)
    {
        Set<String> iPs = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && status.equals(item.getStatus()))
                iPs.add(item.getIp());
        return iPs;
    }

    @Override
    public Set<String> getAllUsers()
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), null, null))
                users.add(item.getUser());
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before))
                users.add(item.getUser());
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before)
    {
        Set<Event> events = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && user.equals(item.getUser()))
                events.add(item.getEvent());
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && ip.equals(item.getIp()))
                users.add(item.getUser());
        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && item.getEvent().equals(Event.LOGIN))
                users.add(item.getUser());
        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && item.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                users.add(item.getUser());
        return users;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && item.getEvent().equals(Event.WRITE_MESSAGE))
                users.add(item.getUser());
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && item.getEvent().equals(Event.SOLVE_TASK))
                users.add(item.getUser());
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && item.getEvent().equals(Event.SOLVE_TASK) && (task == item.taskNumber))
                users.add(item.getUser());
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && item.getEvent().equals(Event.DONE_TASK))
                users.add(item.getUser());
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task)
    {
        Set<String> users = new HashSet<>();
        for (LogItem item : logList)
            if (isIncludeInterval(item.getDate(), after, before) && item.getEvent().equals(Event.DONE_TASK) && (task == item.taskNumber))
                users.add(item.getUser());
        return users;
    }

    private List<Date> getDatesBy(Date after, Date before, String user, String ip, Event event, Status status, Integer task)
    {
        List<Date> list = new ArrayList<Date>();
        for (LogItem item : logList)
        {
            if (!isIncludeInterval(item.getDate(), after, before))
                continue;
            if (user != null && !item.getUser().equals(user))
                continue;
            if (ip != null && !item.getIp().equals(ip))
                continue;
            if (event != null && !item.getEvent().equals(event))
                continue;
            if (status != null && !item.getStatus().equals(status))
                continue;
            if (task != null && !(item.getTaskNumber().equals(task)))
                continue;
            list.add(item.getDate());
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
    {
        return new HashSet<>(getDatesBy(after, before, user, null, event, null, null));
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before)
    {
        return new HashSet<>(getDatesBy(after, before, null, null, null, Status.FAILED, null));
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before)
    {
        return new HashSet<>(getDatesBy(after, before, null, null, null, Status.ERROR, null));
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before)
    {
        List<Date> list = getDatesBy(after, before, user, null, Event.LOGIN, null, null);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before)
    {
        List<Date> list = getDatesBy(after, before, user, null, Event.SOLVE_TASK, null, task);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before)
    {
        List<Date> list = getDatesBy(after, before, user, null, Event.DONE_TASK, null, task);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before)
    {
        return new HashSet<>(getDatesBy(after, before, user, null, Event.WRITE_MESSAGE, null, null));
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before)
    {
        return new HashSet<>(getDatesBy(after, before, user, null, Event.DOWNLOAD_PLUGIN, null, null));
    }

    private List<Event> getEventsBy(Date after, Date before, String user, String ip, Event event, Status status, Integer task)
    {
        List<Event> list = new ArrayList<Event>();
        for (LogItem item : logList)
        {
            if (!isIncludeInterval(item.getDate(), after, before))
                continue;
            if (user != null && !item.getUser().equals(user))
                continue;
            if (ip != null && !item.getIp().equals(ip))
                continue;
            if (event != null && !item.getEvent().equals(event))
                continue;
            if (status != null && !item.getStatus().equals(status))
                continue;
            if (task != null && !(item.getTaskNumber().equals(task)))
                continue;
            list.add(item.getEvent());
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before)
    {
        return (new HashSet<>(getEventsBy(after, before, null, null, null, null, null))).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before)
    {
        return new HashSet<>(getEventsBy(after, before, null, null, null, null, null));
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before)
    {
        return new HashSet<>(getEventsBy(after, before, null, ip, null, null, null));
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before)
    {
        return new HashSet<>(getEventsBy(after, before, user, null, null, null, null));
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before)
    {
        return new HashSet<>(getEventsBy(after, before, null, null, null, Status.FAILED, null));
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before)
    {
        return new HashSet<>(getEventsBy(after, before, null, null, null, Status.ERROR, null));
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before)
    {
        return getEventsBy(after, before, null, null, Event.SOLVE_TASK, null, task).size();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before)
    {
        return getEventsBy(after, before, null, null, Event.DONE_TASK, Status.OK, task).size();
    }


    private Map<Integer, Integer> getAllTasksAndTheirNumber(Date after, Date before, Event event, Status status)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (LogItem item : logList)
        {
            if (!isIncludeInterval(item.getDate(), after, before))
                continue;
            if (event != null && !item.getEvent().equals(event))
                continue;
            if (status != null && !item.getStatus().equals(status))
                continue;
            map.put(item.getTaskNumber(), map.get(item.getTaskNumber()) == null ? 1 : map.get(item.getTaskNumber()) + 1);
        }
        return map;
    }


    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before)
    {
        return getAllTasksAndTheirNumber(after, before, Event.SOLVE_TASK, null);
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before)
    {
        return getAllTasksAndTheirNumber(after, before, Event.DONE_TASK, null);
    }

    @Override
    public Set<Object> execute(String query)
    {
        if (query == null || query.isEmpty())
            return null;
        Set<Object> set = null;
        try
        {
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

            Pattern p = Pattern.compile("get\\s+(ip|user|date|event|status)"
                    + "(\\s+for\\s+(ip|user|date|event|status)\\s?=\\s?\"(.*?)\")?"
                    + "(\\s+and)?(\\s+date\\s+between\\s+\"(.*?)\"\\s+and\\s+\"(.*?)\")?");
            Matcher matcher = p.matcher(query);
            if (matcher.find())
            {
                String field1 = matcher.group(1) != null ? matcher.group(1) : null;
                String field2 = matcher.group(3) != null ? matcher.group(3) : null;
                String value = matcher.group(4) != null ? matcher.group(4) : null;
                Date after = matcher.group(7) != null ? format.parse(matcher.group(7)) : null;
                Date before = matcher.group(8) != null ? format.parse(matcher.group(8)) : null;

                set = new HashSet<>(getListLogByField(field1, getFilteredLog(logList, field2, value, after, before)));
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return set;
    }

    private List<Object> getListLogByField(String field, List<LogItem> source)
    {
        List<Object> list = new ArrayList<>();

        for (LogItem item : source)
        {
            switch (field.toLowerCase())
            {
                case "ip": list.add(item.getIp()); break;
                case "user": list.add(item.getUser()); break;
                case "date": list.add(item.getDate()); break;
                case "event": list.add(item.getEvent()); break;
                case "status": list.add(item.getStatus()); break;
            }
        }
        return list;
    }

    private List<LogItem> getFilteredLog(List<LogItem> source, String field, String value, Date after, Date before) throws ParseException
    {
        List<LogItem> list = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

        for (LogItem item : source)
        {
            if ("ip".equals(field) && value.equals(item.getIp()) && isIncludeInterval(item.getDate(), after, before)
                    || ("user".equals(field) && item.getUser().contains(value)) && isIncludeInterval(item.getDate(), after, before)
                    || ("date".equals(field) && format.parse(value).equals(item.getDate())) && isIncludeInterval(item.getDate(), after, before)
                    || ("event".equals(field) && Event.valueOf(value).equals(item.getEvent())) && isIncludeInterval(item.getDate(), after, before)
                    || ("status".equals(field) && Status.valueOf(value).equals(item.getStatus())) && isIncludeInterval(item.getDate(), after, before)
                    || (field == null) && isIncludeInterval(item.getDate(), after, before)
                    || (value == null) && isIncludeInterval(item.getDate(), after, before)
                    )
                list.add(item);
        }
        return list;
    }

    private void loadLogsFromPath(Path logDir)
    {
        File dir = logDir.toFile();
        String[] logsFileName = dir.list();

        for (String logFileName : logsFileName)
        {
            if (!logFileName.endsWith(".log"))
                continue;
            try (BufferedReader reader = new BufferedReader(new FileReader(dir + File.separator + logFileName)))
            {
                while(reader.ready())
                    parseLogString(reader.readLine());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void parseLogString(String logString) throws ParseException
    {
        String[] elements = logString.split("\t");
        if(elements.length == 5)
        {
            String ip = elements[0];
            String user = elements[1];

            DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
            Date date = format.parse(elements[2]);

            Event event;
            Integer taskNumber;

            if (elements[3].split(" ").length == 2 )
            {
                event = Event.valueOf(elements[3].split(" ")[0]);
                taskNumber = Integer.parseInt(elements[3].split(" ")[1]);
            }
            else
            {
                event = Event.valueOf(elements[3]);
                taskNumber = null;
            }

            Status status = Status.valueOf(elements[4]);

            LogItem logItem = new LogItem(ip, user, date, event, taskNumber, status);
            logList.add(logItem);
        }
    }

    private boolean isIncludeInterval (Date date, Date after, Date before)
    {
        if (after == null && before == null)
            return true;
        if (after == null)
            return (date.compareTo(before) <= 0);
        if (before == null)
            return (date.compareTo(after) >= 0);
        return ((date.compareTo(before) <= 0) && (date.compareTo(after) >= 0));
    }

    private class LogItem {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private Integer taskNumber;
        private Status status;

        public LogItem(String ip, String user, Date date, Event event, Integer taskNumber, Status status)
        {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.taskNumber = taskNumber;
            this.status = status;
        }

        public String getIp()
        {
            return ip;
        }

        public String getUser()
        {
            return user;
        }

        public Date getDate()
        {
            return date;
        }

        public Event getEvent()
        {
            return event;
        }

        public Integer getTaskNumber()
        {
            return taskNumber;
        }

        public Status getStatus()
        {
            return status;
        }

        @Override
        public String toString()
        {
            return "LogItem{" +
                    "ip='" + ip + '\'' +
                    ", user='" + user + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", taskNumber=" + taskNumber +
                    ", status=" + status +
                    '}';
        }
    }
}

