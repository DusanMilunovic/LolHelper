using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net;
using RestSharp;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.IO;
using Newtonsoft.Json;

namespace HeroPickerFront
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window, INotifyPropertyChanged
    {
        private WindowSnapper _snapper;
        private readonly System.Timers.Timer _timer;
        private int lastSelectedButton = 0;

        string _MainContent;
        public string MainContent
        {
            get { return _MainContent; }
            set
            {
                _MainContent = value;
                NotifyPropertyChanged("MainContent");
            }
        }

        string _ChampionName;
        public string ChampionName {
            get { return _ChampionName; }
            protected set
            {
                _ChampionName = value;
                NotifyPropertyChanged("ChampionName");
            }
        }

        string _Position;
        public string Position {
            get { return _Position; }
            protected set
            {
                _Position = value;
                NotifyPropertyChanged("Position");
            }
        }
        
        string _StrongPoint;
        public string StrongPoint {
            get { return _StrongPoint; }
            protected set
            {
                _StrongPoint = value;
                NotifyPropertyChanged("StrongPoint");
            }
        }
        string _Classes;
        public string Classes {
            get { return _Classes; }
            protected set
            {
                _Classes = value;
                NotifyPropertyChanged("Classes");
            }
        }
        string _Counters;
        public string Counters {
            get { return _Counters; }
            protected set
            {
                _Counters = value;
                NotifyPropertyChanged("Counters");
            }
        }
        string _Champion0;
        public string Champion0 {
            get { return _Champion0; }
            protected set
            {
                _Champion0 = value;
                NotifyPropertyChanged("Champion0");
            }
        }
        string _Champion1;
        public string Champion1 {
            get { return _Champion1; }
            protected set
            {
                _Champion1 = value;
                NotifyPropertyChanged("Champion1");
            }
        }
        string _Champion2;
        public string Champion2 {
            get { return _Champion2; }
            protected set
            {
                _Champion2 = value;
                NotifyPropertyChanged("Champion2");
            }
        }
        string _Champion3;
        public string Champion3 {
            get { return _Champion3; }
            protected set
            {
                _Champion3 = value;
                NotifyPropertyChanged("Champion3");
            }
        }
        string _Champion4;
        public string Champion4 {
            get { return _Champion4; }
            protected set
            {
                _Champion4 = value;
                NotifyPropertyChanged("Champion4");
            }
        }
        string _Champion5;
        public string Champion5 {
            get { return _Champion5; }
            protected set
            {
                _Champion5 = value;
                NotifyPropertyChanged("Champion5");
            }
        }
        string _Champion6;
        public string Champion6 {
            get { return _Champion6; }
            protected set
            {
                _Champion6 = value;
                NotifyPropertyChanged("Champion6");
            }
        }
        string _Champion7;
        public string Champion7 {
            get { return _Champion7; }
            protected set
            {
                _Champion7 = value;
                NotifyPropertyChanged("Champion7");
            }
        }
        string _Champion8;
        public string Champion8 {
            get { return _Champion8; }
            protected set
            {
                _Champion8 = value;
                NotifyPropertyChanged("Champion8");
            }
        }
        string _Champion9;
        public string Champion9 {
            get { return _Champion9; }
            protected set
            {
                _Champion9 = value;
                NotifyPropertyChanged("Champion9");
            }
        }

        ChampionPicksAndChampionSelect cpcs { get; set; }


        public MainWindow()
        {

            InitializeComponent();
            DataContext = this;
            _snapper = new WindowSnapper(this, "League of Legends");
            _snapper.Attach();
            _timer = new System.Timers.Timer(1000*10); 
            _timer.Elapsed += new ElapsedEventHandler(OnTimedEvent);            
            _timer.AutoReset = true;
            _timer.Enabled = true;
            Champion0 = "?";
            Champion1 = "?";
            Champion2 = "?";
            Champion3 = "?";
            Champion4 = "?";
            Champion5 = "?";
            Champion6 = "?";
            Champion7 = "?";
            Champion8 = "?";
            Champion9 = "?";
            ChampionName = "";
            Position = "";
            StrongPoint = "";
            Counters = "";
            Classes = "";
            MainContent = "";
        }

        private void ResetUI(bool resetChampContent)
        {
            Champion0 = "?";
            Champion1 = "?";
            Champion2 = "?";
            Champion3 = "?";
            Champion4 = "?";
            Champion5 = "?";
            Champion6 = "?";
            Champion7 = "?";
            Champion8 = "?";
            Champion9 = "?";
            if (resetChampContent)
            {
                ChampionName = "";
                Position = "";
                StrongPoint = "";
                Counters = "";
                Classes = "";
            }
            MainContent = "";

        }

        private void UpdateUI(bool pickPhase)
        {
            ResetUI(false);
            cpcs.Picks.ForEach(pick =>
            {
                if (pick.MyTeam)
                {
                    if (pick.Position == "top")
                    {
                        Champion0 = pick.Champion;
                    }
                    else if (pick.Position == "jungle")
                    {
                        Champion1 = pick.Champion;
                    } 
                    else if (pick.Position == "mid")
                    {
                        Champion2 = pick.Champion;
                    } 
                    else if (pick.Position == "bot")
                    {
                        Champion3 = pick.Champion;
                    } 
                    else if (pick.Position == "support")
                    {
                        Champion4 = pick.Champion;
                    } 
                }
                else
                {
                    if (pick.Position == "top")
                    {
                        Champion5 = pick.Champion;
                    }
                    else if (pick.Position == "jungle")
                    {
                        Champion6 = pick.Champion;
                    } 
                    else if (pick.Position == "mid")
                    {
                        Champion7 = pick.Champion;
                    } 
                    else if (pick.Position == "bot")
                    {
                        Champion8 = pick.Champion;
                    } 
                    else if (pick.Position == "support")
                    {
                        Champion9 = pick.Champion;
                    } 
                }
            });
            if (pickPhase)
            {
                if (cpcs.ChampionSelect.EnemyTeamComposition.Count() > 0)
                {
                    MainContent += "Enemy team composition is: ";
                    for (int i = 0; i < cpcs.ChampionSelect.EnemyTeamComposition.Count(); i++)
                    {
                        MainContent += cpcs.ChampionSelect.EnemyTeamComposition[i];
                        if (i < cpcs.ChampionSelect.EnemyTeamComposition.Count() - 1)
                        {
                            MainContent += ", ";
                        }
                    }
                    MainContent += "\n\n";
                }
                List<string> bestChampions = cpcs.ChampionSelect.ChampsForPosition.ToList();
                List<string> compCounters = new List<string>();
                foreach (KeyValuePair<string, List<string>> entry in cpcs.ChampionSelect.CompositionCounters)
                {
                    compCounters.AddRange(entry.Value);
                }
                compCounters = compCounters.Distinct().ToList();
                bestChampions = bestChampions.Intersect(compCounters).ToList();
                if (bestChampions.Count() > 0)
                {
                    MainContent += "The best champions for this game are: \n";
                    for (int i = 0; i < bestChampions.Count(); i++)
                    {
                        MainContent += bestChampions[i];
                        if (i < bestChampions.Count() - 1)
                        {
                            MainContent += ", ";
                        }
                    }
                    MainContent += "\nThese champions are good against their team composition and are playable in the " + cpcs.ChampionSelect.MyPosition + " position \n \n";
                } 

                List<string> positionChampions = cpcs.ChampionSelect.ChampsForPosition.ToList();
                positionChampions = positionChampions.Except(bestChampions).ToList();
                if (positionChampions.Count() > 0)
                {
                    if (bestChampions.Count() > 0)
                    {
                        MainContent += "Some other champions for the " + cpcs.ChampionSelect.MyPosition + " position: ";
                    }
                    else
                    {
                        MainContent += "Good champions for the " + cpcs.ChampionSelect.MyPosition + " position: ";
                    }
                    for (int i = 0; i < positionChampions.Count(); i++)
                    {
                        MainContent += positionChampions[i];
                        if (i < positionChampions.Count() - 1)
                        {
                            MainContent += ", ";
                        }
                    }
                    MainContent += "\n \n";
                }
                
                foreach (KeyValuePair<string, List<string>> entry in cpcs.ChampionSelect.CompositionCounters)
                {
                    MainContent += "Good champions against " + entry.Key + " composition are: ";
                    for (int i = 0; i < entry.Value.Count(); i++)
                    {
                        MainContent += entry.Value[i];
                        if (i < entry.Value.Count() - 1)
                        {
                            MainContent += ", ";
                        }
                    }
                    MainContent += "\n \n";
                }

            }
            else
            {
                if (cpcs.ChampionSelect.CompositionCountersAdvice.Count() > 0)
                {
                    foreach(var advice in cpcs.ChampionSelect.CompositionCountersAdvice) {
                        MainContent += advice;
                    }
                }
            }
        }
        private void ButtonClick(object sender, EventArgs e)
        {
            Button clicked = (Button)sender;
            if (clicked.Name == "Button0")
            {
                ChangeSelection(Champion0);
            }
            else if (clicked.Name == "Button1")
            {
                ChangeSelection(Champion1);
            }
            else if (clicked.Name == "Button2")
            {
                ChangeSelection(Champion2);
            }
            else if (clicked.Name == "Button3")
            {
                ChangeSelection(Champion3);
            }
            else if (clicked.Name == "Button4")
            {
                ChangeSelection(Champion4);
            }
            else if (clicked.Name == "Button5")
            {
                ChangeSelection(Champion5);
            }
            else if (clicked.Name == "Button6")
            {
                ChangeSelection(Champion6);
            }
            else if (clicked.Name == "Button7")
            {
                ChangeSelection(Champion7);
            }
            else if (clicked.Name == "Button8")
            {
                ChangeSelection(Champion8);
            }
            else if (clicked.Name == "Button9")
            {
                ChangeSelection(Champion9);
            }
        }

        private void ChangeSelection(string champion)
        {
            if (champion == "?")
            {
                ChampionName = "";
                Position = "";
                Counters = "";
                Classes = "";
                StrongPoint = "";
            }
            else
            {
                foreach(var pick in cpcs.Picks)
                {
                    if (pick.Champion == champion)
                    {
                        ChampionName = champion;
                        Position = pick.Position;
                        Counters = string.Join(", ", pick.CounterPicks);
                        Classes = string.Join(", ", pick.Classes);
                        StrongPoint = pick.StrongPoint;
                    }
                }
            }
        }

        private void OnTimedEvent(object source, ElapsedEventArgs e)
        {
            ClientCommunicator clientCommunicator = new ClientCommunicator();
            Task<ChampionPicksTurnAndPosition> retval = clientCommunicator.getChampionsPicked();
            try
            {
                retval.Wait();
            } catch
            {
                Console.WriteLine("Cant contact the client.");
                //todo uncomment
                //ResetUI(true);
                return;
            }

            var result = retval.Result;

            Console.WriteLine("SEND IT");
            if (result.picks.Count() > 0 && result.picks.Count() < 10)
            {
                Console.WriteLine("PICK---------------------------------------------------------------");
                var client = new RestClient("http://localhost:9090/api/pick");
                var request = new RestRequest(Method.POST);
                request.AddHeader("cache-control", "no-cache");
                request.AddHeader("content-type", "application/json");
                ChampionPicksAndPosition cpp = new ChampionPicksAndPosition(result.picks, result.position);
                request.AddParameter("application/json", cpp.toJson(), ParameterType.RequestBody);
                //IRestResponse response = client.Execute(request);
                Task<ChampionPicksAndChampionSelect> retval2 = client.PostAsync<ChampionPicksAndChampionSelect>(request);
                retval2.Wait();
                if (retval2.Result == null)
                {
                    ResetUI(true);
                    MainContent = "There has been too many requests from your IP address. If this is a false alert, try to use the service later again.";
                }
                else {
                    cpcs = retval2.Result;
                    UpdateUI(true);
                }
            }
            else if (result.picks.Count() == 10)
            {
                Console.WriteLine("ADVICE ---------------------------------------------------------------");
                var client = new RestClient("http://localhost:9090/api/advice");
                var request = new RestRequest(Method.POST);
                request.AddHeader("cache-control", "no-cache");
                request.AddHeader("content-type", "application/json");
                ChampionPicksAndPosition cpp = new ChampionPicksAndPosition(result.picks, result.position);
                request.AddParameter("application/json", cpp.toJson(), ParameterType.RequestBody);
                //IRestResponse response = client.Execute(request);
                Task<ChampionPicksAndChampionSelect> retval2 = client.PostAsync<ChampionPicksAndChampionSelect>(request);
                retval2.Wait();
                if (retval2.Result == null)
                {
                    ResetUI(true);
                    MainContent = "There has been too many requests from your IP address. If this is a false alert, try to use the service later again.";
                } else
                {
                    cpcs = retval2.Result;
                    UpdateUI(false);
                }
            }
        }
        public event PropertyChangedEventHandler PropertyChanged;
        public void NotifyPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }

        private void EditDroolsFiles_Click(object sender, RoutedEventArgs e)
        {
            EditDroolsFilesDialog dlg = new EditDroolsFilesDialog();
            dlg.Owner = this;

            // Open the dialog box modally
            dlg.ShowDialog();
        }

        private void Relationship_Click(object sender, RoutedEventArgs e)
        {
            CheckRelationshipsDialog dlg = new CheckRelationshipsDialog();
            dlg.Owner = this;

            // Open the dialog box modally
            dlg.ShowDialog();
        }
    }
}
