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

namespace WindowAttacher
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private WindowSnapper _snapper;
        private readonly System.Timers.Timer _timer;
        private bool picksGiven = false;


        public MainWindow()
        {
            InitializeComponent();
            _snapper = new WindowSnapper(this, "League of Legends");
            _snapper.Attach();
            _timer = new System.Timers.Timer(1000*10); 
            _timer.Elapsed += new ElapsedEventHandler(OnTimedEvent);            
            _timer.AutoReset = true;
            _timer.Enabled = true;
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
                return;
            }

            var result = retval.Result;
            //championpick cp1 = new championpick("aatrox", true, false);
            //ChampionPick cp2 = new ChampionPick("Lulu", true, false);
            //ChampionPick cp3 = new ChampionPick("Akali", true, false);
            //ChampionPick cp4 = new ChampionPick("KhaZix", false, false);
            //ChampionPick cp5 = new ChampionPick("Syndra", false, false);
            //ChampionPick cp6 = new ChampionPick("Lux", false, false);
            //ChampionPick cp7 = new ChampionPick("Garen", false, false);
            //List<ChampionPick> champions = new List<ChampionPick>();
            //champions.Add(cp1);
            //champions.Add(cp2);
            //champions.Add(cp3);
            //champions.Add(cp4);
            //champions.Add(cp5);
            //champions.Add(cp6);
            //champions.Add(cp7);

            Console.WriteLine("SEND IT");
            //var result = new ChampionPicksTurnAndPosition(champions, true, "bot"); 
            if (result.myTurn)
            {
                Console.WriteLine("PICK---------------------------------------------------------------");
                var client = new RestClient("http://localhost:9090/api/pick");
                var request = new RestRequest(Method.POST);
                request.AddHeader("cache-control", "no-cache");
                request.AddHeader("content-type", "application/json");
                ChampionPicksAndPosition cpp = new ChampionPicksAndPosition(result.picks, result.position);
                request.AddParameter("application/json", cpp.toJson(), ParameterType.RequestBody);
                IRestResponse response = client.Execute(request);
                Task<ChampionPicksAndChampionSelect> retval2 = client.PostAsync<ChampionPicksAndChampionSelect>(request);
                retval2.Wait();
                ChampionPicksAndChampionSelect cpcs = retval2.Result; 
            }
            else if (result.picks.Count == 10)
            {
                Console.WriteLine("ADVICE ---------------------------------------------------------------");
                var client = new RestClient("http://localhost:9090/api/advice");
                var request = new RestRequest(Method.POST);
                request.AddHeader("cache-control", "no-cache");
                request.AddHeader("content-type", "application/json");
                ChampionPicksAndPosition cpp = new ChampionPicksAndPosition(result.picks, result.position);
                request.AddParameter("application/json", cpp.toJson(), ParameterType.RequestBody);
                IRestResponse response = client.Execute(request);
                Task<ChampionPicksAndChampionSelect> retval2 = client.PostAsync<ChampionPicksAndChampionSelect>(request);
                retval2.Wait();
                ChampionPicksAndChampionSelect cpcs = retval2.Result; 
            }
        }
    }
}
