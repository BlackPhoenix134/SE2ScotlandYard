# SE2ScotlandYard
Hierbei handelt es sich um das Repository der Gruppe 10 für das SE2-Projekt. Mittels Scrum und anderen agilen Methoden wurde versucht, eine eigene Version der Spiel Scotland Yard zu entwickeln und als Android-Game umzusetzen. 


# Spielregeln 
Jeder Spieler wird random auf ein Feld innerhalb des Spielbrettes gestellt, auf dem er seinen ersten Zug beginnt. Die Detektive können sich untereinander sehen, sowie MrX kann
alle Detektive sehen, sein Aufenthaltsort ist zunächst noch geheim. Die Map ist in nummerierte Felder unterteilt, die jeweils mit Motorrad, Drache oder Pferd angefahren werden
können. Die Detektive - sowie auch Mister X - dürfen sich auf dem Spielplan mit den dementsprechenden Tickets fortbewegen. Dabei ist zu beachten, dass nicht alle Felder mit
allen Verkehrsmitteln angefahren werden können. Eine Fahrt geht außerdem immer nur bis zum nächsten verbundenen Feld der gleichen Art. Jeder Detektiv erhält 10 Motorrad-Tickets,
8 Pferd-Tickets und 4 Drachen-Tickets. Mister X erhält 4 Motorrad- und je 3 Pferd- und Drachen-Tickets, außerdem die beiden Tickets Doppelzug (2x) und soviele von den Black
Tickets, wie Detektive am Spiel teilnehmen.

# Nun beginnt das Spiel
Mister X macht seinen Zug immer zuerst. Dazu verwendet er eins seiner Tickets. Mister X kann sich aber auch entschließen, einen Doppelzug zu machen. Dazu gibt er ein Kärtchen
mit der Aufschrift "2x" ab, und kann dafür 2 Züge hintereinander durchführen. Mister X hat außerdem auch die sog. Black Tickets zu seiner Verfügung. Diese kann er anstelle der
regulären Tickets für jedes beliebige Verkehrsmittel benutzen. Black Tickets können auch in Kombination mit einem Doppelzug verwendet werden. Dies macht es natürlich schwierig
für die Detektive zu ermitteln, wo Mister X sich gerade aufhält. Danach machen die Detektive ihren Zug. Die Detektive dürfen immer nur 1 Ticket in einer Runde benutzen. Sie
behalten die Tickets danach nicht, sondern, diese werden Mister X gutgeschrieben, welcher sie dann weiter verwenden darf. Unter den Detektiven ist die Absprache über das
Vorgehen ausdrücklich erlaubt. Haben alle Detektive ihren Zug gemacht, ist Mister X wieder dran.

Das Spiel endet, wenn es einem Detektiv gelingt, auf das gleiche Feld zu ziehen, auf dem sich auch Mister X befindet. Mister X wird dann enttarnt und das Spiel ist sofort
beendet. Nachdem sich Mister X zum letzten Mal gezeigt hat, ist das Spiel ebenfalls beendet, sollten ihn die Detektive bis dahin nicht gefasst haben.


# How to start a Game 
Zu beginn wird der Spieler vom Start-Bildschirm begrüßt. In weiterer Folge kann der Spieler auswählen, ob er ein Spiel Hosten möchte, oder ob er einem Spiel beitreten möchte.


![image](https://user-images.githubusercontent.com/63232105/120924907-5669b000-c6d6-11eb-890f-de07a724c3dd.png)

# Hosting a Game 
Möchte ein Spieler ein Spiel hosten, so muss dieser nichts weiter tun als seinen gewünschten Spielernamen einzugeben. Die standardmäßigen Ports können verändert werden, müssen dann aber an die anderen Spieler weitergegeben werden. 

# Joining a Game 
Möchte ein Spieler einem Spiel beitreten, so braucht dieser die IP-Adresse des Hosts und, sollten diese vom Host verändert worden sein, beide Ports. Auch hierbei ist wiederum ein Spielernamen auszuwählen. 

![image](https://user-images.githubusercontent.com/63232105/120925039-e6a7f500-c6d6-11eb-9977-740d032f7a57.png)


# Lobby
Ist nun eine Lobby erstellt bzw. ein Spieler einer Lobby beigetreten, so muss gewartet werden, bis alle Spieler bereit sind. Dies wird für die Spieler dadurch ersichtlich, dass nicht nur der Ready Button erscheint, sondern zudem ein Startbutton aufscheint. Um zu sehen, welcher Spieler noch nicht auf Ready gedrückt hat, gibt es neben jedem Spieler einen grünen Haken. 

![image](https://user-images.githubusercontent.com/63232105/120925150-7ea5de80-c6d7-11eb-9af8-30b8b8513918.png)



![image](https://user-images.githubusercontent.com/63232105/120925161-95e4cc00-c6d7-11eb-8634-9d60f2406597.png)


# Camera 
Die Kamera, das geforderte Spielfeature, kann ebenfalls über die Lobby geöffnet werden. Hierbei kann ein Foto des Spielers, oder eines beliebigen Objektes gemacht werden, welches spärter Als Avatar während des Spieles dient. 

![image](https://user-images.githubusercontent.com/63232105/121188836-c9576000-c869-11eb-8061-99700ac82169.png)


# Cheat Function 
Die Cheat-Funktion des Mrx ist es, sich überall auf der Map teleportieren zu können. Um das Spiel ausgeglichener zu gestalten, wird dieser jedoch alle 3 Runden für eine Runde für alle Spieler sichtbar. 

# Das Team 
Kristina
 

Fabian



Johannes
    


Samuel 



# Arbeitsteilung 
# Kristina
    Basic GameBoard
    Asset Manager
    Simple Movement
    Gameplay
    Tickets

# Fabian
    Complete Project Setup 
    Render Pipeline
    Controls 
    All Background Working Stuff
    Lobby Connection 
    Camera implementation 
    Deadline Work 

# Johannes
    Basic Touch Controls 
    Network Syncing 
    Gameplay

# Samuel 
    Screen Manager 
    Scene Management
    UI - Design 
      Transitions
      Sounds
      Buttons
    Screens
    Basic Lobby Connection / Networking
    Basic Camera implementation 
    Deadline-Work 
    
    
# TrelloBoard

# Namensabkürzungen

JL = Johannes Ladurner 

FF aka BP = Fabian Fruhmann / BlackPhoenix 

SM = Samuel Miklau 

KL = Kristina Liebhart 


# Week 1

![image](https://user-images.githubusercontent.com/63232105/122783981-58c13200-d2b2-11eb-8ade-a84b744bc278.png)

# Week 2&3

![image](https://user-images.githubusercontent.com/63232105/122784100-72fb1000-d2b2-11eb-9b9f-28a650ef02a2.png)

# Week 4

![image](https://user-images.githubusercontent.com/63232105/122784175-84dcb300-d2b2-11eb-802a-ac7398b06b1b.png)

# Week 5

![image](https://user-images.githubusercontent.com/63232105/122784221-91610b80-d2b2-11eb-9fbd-1e8fd6eb0aa1.png)

# Week 6

![image](https://user-images.githubusercontent.com/63232105/122784265-9d4ccd80-d2b2-11eb-8948-65997d2e79ac.png)

# Week 7 

![image](https://user-images.githubusercontent.com/63232105/122784324-a9388f80-d2b2-11eb-8a64-38287f9b497d.png)

# Week 8 
![image](https://user-images.githubusercontent.com/63232105/122784388-b9506f00-d2b2-11eb-859d-c112bfec82b8.png)

# Day before Deadline 

![image](https://user-images.githubusercontent.com/63232105/122784460-c66d5e00-d2b2-11eb-866f-664bf9d47437.png)






