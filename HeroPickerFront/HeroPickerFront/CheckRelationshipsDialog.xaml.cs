using RestSharp;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace HeroPickerFront
{
    /// <summary>
    /// Interaction logic for CheckRelationshipsDialog.xaml
    /// </summary>
    public partial class CheckRelationshipsDialog : Window, INotifyPropertyChanged
    {
        private string _Message;
        public string Message
        {
            get { return _Message; }
            set
            {
                _Message = value;
                NotifyPropertyChanged("Message");
            }
        }

        public CheckRelationshipsDialog()
        {
            InitializeComponent();
            DataContext = this;
        }

        public event PropertyChangedEventHandler PropertyChanged;
        public void NotifyPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
            }
        }

        private void CheckRelationshipButton_Click(object sender, RoutedEventArgs e)
        {
            String champion1 = Champion1.Text;
            String champion2 = Champion2.Text;
            if (champion1 == champion2)
            {
                Message = "Please choose a pair of different champions";
            } else
            {
                Thread t = new Thread(() => SendMessage(champion1, champion2));
                t.Start();
            }
        }

        private void SendMessage(String champion1, String champion2)
        {
            var client = new RestClient("http://localhost:9090/relations/" + champion1 + "/" + champion2);
            var request = new RestRequest(Method.GET);
            request.AddHeader("cache-control", "no-cache");
            request.AddHeader("content-type", "application/json");
            Task<Message> retval2 = client.GetAsync<Message>(request);
            retval2.Wait();

            if (retval2.Result == null)
            {
                Message = "There has been too many requests from your IP address. If this is a false alert, try to use the service later again.";
            }
            else
            {
                Message result = retval2.Result;
                this.Dispatcher.BeginInvoke(new System.Action(() =>
                {
                    if (result.message == "None")
                    {
                        Message = "There is no relationship between these two champions";
                    } else
                    {
                        Message = "These two champions are " + result.message;
                    }
                }));
            }
        }
    }
}
