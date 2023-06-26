fetch('statistics/getData')
    .then(function(response) {
        if (!response.ok) {
            throw new Error('Network response was not OK');
        }
        return response.json();
    })
    .then(function(data) {
        const customLabel = data.map((item) => item["title"]);
        const customData = data.map((item) => item["totalHours"]);

        // Get the canvas element from the HTML file
        var chartCanvas = document.getElementById('chart1');

        // Define the data for the chart
        var chartData = {
            labels: customLabel,
            datasets: [{
                label: '# of hours',
                data: customData,
            }]
        };

        // Create the bar chart
        var chart1 = new Chart(chartCanvas, {
            //title: 'Hours per project',
            type: 'bar',
            data: chartData,
            options: {
                responsive: true, // Make the chart responsive
                scales: {
                    x: {
                        grid: {
                            display: false // Hide the x-axis grid lines
                        }
                    },
                    y: {
                        beginAtZero: true // Start the y-axis at zero
                    }
                }
            }
        });
    })
    .catch(function(error) {
        console.log('Error fetching data:', error);
    });

fetch('statistics/getData2')
    .then(function(response) {
        if (!response.ok) {
            throw new Error('Network response was not OK');
        }
        return response.json();
    })
        .then(function(data) {
        const customLabel =  data.map(row => row.user);
        const customData = data.map(row => row.count);
        console.log(customLabel);
        console.log(customData);

        // Get the canvas element from the HTML file
        var chartCanvas = document.getElementById('chart2');

        // Define the data for the chart
        var chartData = {
            labels: customLabel,
            datasets: [{
                label: '# of events dedicated',
                data: customData,
            }]
        };

        // Create the bar chart
        var chart2 = new Chart(chartCanvas, {
            //title: 'Hours per project',
            type: 'bar',
            data: chartData,
            options: {
                responsive: true, // Make the chart responsive
                scales: {
                    x: {
                        grid: {
                            display: false // Hide the x-axis grid lines
                        }
                    },
                    y: {
                        beginAtZero: true // Start the y-axis at zero
                    }
                }
            }
        });
    })
    .catch(function(error) {
        console.log('Error fetching data:', error);
    });

