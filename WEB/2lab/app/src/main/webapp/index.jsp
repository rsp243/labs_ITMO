<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Reshetov Semen P3206 WEB 2lab</title>
    <meta name="description" content="Reshetov Semen P3206 WEB 2lab">
    <meta name="author" content="Reshetov Semen">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Nunito+Sans:opsz@6..12&family=Poppins:wght@400;500;600;700&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" href="src/css/styles.css">
</head>

<body>
    <header>
        <div class="introduction">
            <h3>First web laboratory</h3>
            <strong>Reshetov Semen, P3206. Variant: 1623</strong>
        </div>
        <div class="links">
            <form action="https://se.ifmo.ru/courses/web" target="_blank">
                <button class="nav-button">
                    SE VITMO
                </button>
            </form>
            <form action="https://github.com/rsp243" target="_blank">
                <button class="nav-button">
                    Github account
                </button>
            </form>
            <form action="https://t.me/rsp243" target="_blank">
                <button class="nav-button">
                    Telegram account
                </button>
            </form>
        </div>
    </header>

    <div class="main-container">
        <div class="canvas-and-selection">
            <canvas id="canvas" width="300" height="300"></canvas>
            <div class="params-selection">
                <div class="Xselection">
                    <p>Choose X value</p>
                    <p class="X-error-message error-message">X value have to be whole number between -3 and 5
                        (including)</p>
                    <input type="radio" id="contactChoice1" name="Xvalue" value="-3" />
                    <label for="contactChoice1">-3</label>
                    <input type="radio" id="contactChoice2" name="Xvalue" value="-2" />
                    <label for="contactChoice2">-2</label>
                    <input type="radio" id="contactChoice3" name="Xvalue" value="-1" />
                    <label for="contactChoice3">-1</label>
                    <input type="radio" id="contactChoice4" name="Xvalue" value="0" checked />
                    <label for="contactChoice4">0</label>
                    <input type="radio" id="contactChoice5" name="Xvalue" value="1" />
                    <label for="contactChoice5">1</label>
                    <input type="radio" id="contactChoice6" name="Xvalue" value="2" />
                    <label for="contactChoice6">2</label>
                    <input type="radio" id="contactChoice7" name="Xvalue" value="3" />
                    <label for="contactChoice7">3</label>
                    <input type="radio" id="contactChoice8" name="Xvalue" value="4" />
                    <label for="contactChoice8">4</label>
                    <input type="radio" id="contactChoice9" name="Xvalue" value="5" />
                    <label for="contactChoice9">5</label>
                </div>
                <div class="Yselection">
                    <p>Choose Y value</p>
                    <p class="Y-error-message error-message">Y value have to be float number between -3 and 5 (not
                        including)</p>
                    <input class="Yselection-text" type="text" id="Yselection" name="Yvalue" placeholder="Y value" maxlength="15" />
                </div>
                <div class="Rselection">
                    <p>Choose R value</p>
                    <p class="R-error-message error-message">R value have to be float number between 1 and 3 (including)
                    </p>
                    <select id="Rselection" name="Rvalue">
                        <option value="1" selected>1</option>
                        <option value="1.5">1.5</option>
                        <option value="2">2</option>
                        <option value="2.5">2.5</option>
                        <option value="3">3</option>
                    </select>
                </div>
            </div>
            <div class="btn-block">
                <button class="control-btn btn-process">Process</button>
                <button class="control-btn btn-clear">Clear table</button>
            </div>
        </div>
        <div class="data-table">
            <table cellspacing="1" cellpadding="10" width="100%">

                <thead>
                    <tr>
                        <th scope="col">X Value</th>
                        <th scope="col">Y Value</th>
                        <th scope="col">R Value</th>
                        <th scope="col">Hit/Miss</th>
                        <th scope="col">Event time</th>
                        <th scope="col">Execution time, ms</th>
                        <th scope="col">Color(RGB)</th>
                    </tr>
                </thead>
                <tbody class="table-body">
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"
        integrity="sha256-lSjKY0/srUM9BE3dPm+c4fBo1dky2v27Gdjm2uoZaL0=" crossorigin="anonymous"></script>
    <script type="module" src="src/js/canvas.js"></script>
    <script type="module" src="src/js/validation.js"></script>
    <script type="module" src="src/js/table.js"></script>
    <script type="module" src="src/js/btn-events.js"></script>
</body>

</html>⏎ 