using RestSharp;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace HeroPickerFront
{
    /// <summary>
    /// Interaction logic for EditDroolsFilesDialog.xaml
    /// </summary>
    public partial class EditDroolsFilesDialog : Window, INotifyPropertyChanged
    {
        private List<string> selectedCounters = new List<string>();
        private static int countersNeeded = 8;
        private string _LeftToSelect = countersNeeded.ToString(); 
        public string LeftToSelect {
            get { return _LeftToSelect; }
            protected set
            {
                _LeftToSelect = value;
                NotifyPropertyChanged("LeftToSelect");
            }
        }
        private string _ErrorMessage; 
        public string ErrorMessage {
            get { return _ErrorMessage; }
            protected set
            {
                _ErrorMessage = value;
                NotifyPropertyChanged("ErrorMessage");
            }
        }

        public EditDroolsFilesDialog()
        {
            InitializeComponent();
            DataContext = this;
        }

        private void SelectCounterPicks_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (SelectCounterPicks.SelectedItems != null)
            {
                selectedCounters = new List<string>();
                foreach (System.Windows.Controls.ListBoxItem selectedCounter in SelectCounterPicks.SelectedItems)
                {
                    selectedCounters.Add((string)selectedCounter.Content);
                }
            }
            LeftToSelect = (countersNeeded - selectedCounters.Count()).ToString();
            if (LeftToSelect == "0")
            {
                UploadButton.IsEnabled = true;
            } else
            {
                UploadButton.IsEnabled = false;
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

        private void UploadButton_Click(object sender, RoutedEventArgs e)
        {
            int composition = CompositionNumber.SelectedIndex + 1;
            int adChampions = AdChampions.SelectedIndex;
            int apChampions= ApChampions.SelectedIndex;
            int tankChampions = TankChampions.SelectedIndex;
            int healerChampions = HealerChampions.SelectedIndex;
            int pokeChampions = PokeChampions.SelectedIndex;
            CompositionRule cr = new CompositionRule
            {
                Composition = composition,
                AdChampions = adChampions,
                ApChampions = apChampions,
                TankChampions = tankChampions,
                HealerChampions = healerChampions,
                PokeChampions = pokeChampions,
                Counters = selectedCounters
            };
            Thread t = new Thread(() => SendMessage(cr));
            t.Start();
        }

        private void SendMessage(CompositionRule cr)
        {
            var client = new RestClient("http://localhost:9090/api/newRule");
            var request = new RestRequest(Method.POST);
            request.AddHeader("cache-control", "no-cache");
            request.AddHeader("content-type", "application/json");
            request.AddParameter("application/json", cr.ToJson(), ParameterType.RequestBody);
            Task<Message> retval2 = client.PostAsync<Message>(request);
            retval2.Wait();

            if (retval2.Result == null)
            {
                ErrorMessage = "There has been too many requests from your IP address. If this is a false alert, try to use the service later again.";
            }
            else
            {
                Message result = retval2.Result;
                this.Dispatcher.BeginInvoke(new System.Action(() =>
                {
                    if (result.message == "Success")
                    {
                        this.Close();
                    }
                    else
                    {
                        ErrorMessage = result.message;
                    }
                }));
            }
        }
    }
}
