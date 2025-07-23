import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SudokuGame extends JFrame {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;

    private int[][] board;
    private boolean[][] fixedCells;
    private JTextField[][] cellFields;
    private JPanel boardPanel;
    private JPanel controlPanel;

    public SudokuGame() {
        initializeGame();
        initializeUI();
    }

    private void initializeGame() {
        board = new int[SIZE][SIZE];
        fixedCells = new boolean[SIZE][SIZE];
        cellFields = new JTextField[SIZE][SIZE];
    }

    private void initializeUI() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createBoardPanel();
        createControlPanel();

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void createBoardPanel() {
        boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField field = new JTextField(2);
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(new Font("Arial", Font.BOLD, 20));

                // Define larguras das bordas para as linhas internas e externas
                int top = (row % SUBGRID_SIZE == 0) ? 3 : 1;
                int left = (col % SUBGRID_SIZE == 0) ? 3 : 1;
                int bottom = (row == SIZE - 1) ? 3 : 1;
                int right = (col == SIZE - 1) ? 3 : 1;

                Border border = new MatteBorder(top, left, bottom, right, Color.BLACK);
                field.setBorder(border);

                cellFields[row][col] = field;
                boardPanel.add(field);
            }
        }
    }

    private void createControlPanel() {
        controlPanel = new JPanel(new FlowLayout());

        JButton newGameBtn = new JButton("Novo Jogo");
        JButton addNumberBtn = new JButton("Adicionar Número");
        JButton removeNumberBtn = new JButton("Remover Número");
        JButton checkGameBtn = new JButton("Verificar Jogo");
        JButton gameStatusBtn = new JButton("Status do Jogo");
        JButton clearBtn = new JButton("Limpar");
        JButton finishBtn = new JButton("Finalizar");

        controlPanel.add(newGameBtn);
        controlPanel.add(addNumberBtn);
        controlPanel.add(removeNumberBtn);
        controlPanel.add(checkGameBtn);
        controlPanel.add(gameStatusBtn);
        controlPanel.add(clearBtn);
        controlPanel.add(finishBtn);

        newGameBtn.addActionListener(e -> startNewGame());
        addNumberBtn.addActionListener(e -> addNumber());
        removeNumberBtn.addActionListener(e -> removeNumber());
        checkGameBtn.addActionListener(e -> checkGame());
        gameStatusBtn.addActionListener(e -> showGameStatus());
        clearBtn.addActionListener(e -> clearBoard());
        finishBtn.addActionListener(e -> finishGame());
    }

    private void startNewGame() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = 0;
                fixedCells[row][col] = false;
                cellFields[row][col].setText("");
                cellFields[row][col].setEditable(true);
                cellFields[row][col].setBackground(Color.WHITE);
            }
        }

        String input = JOptionPane.showInputDialog(this,
                "Digite os números iniciais no formato: linha,coluna,valor;linha,coluna,valor;...\nEx: 0,0,5;0,1,3;...");

        if (input != null && !input.trim().isEmpty()) {
            String[] parts = input.split(";");
            for (String part : parts) {
                try {
                    String[] values = part.split(",");
                    int row = Integer.parseInt(values[0]);
                    int col = Integer.parseInt(values[1]);
                    int value = Integer.parseInt(values[2]);

                    if (isValidPosition(row, col) && isValidValue(value)) {
                        board[row][col] = value;
                        fixedCells[row][col] = true;
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Entrada inválida ignorada: " + part,
                                "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                            "Formato inválido para: " + part,
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        updateBoardDisplay();
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    private boolean isValidValue(int value) {
        return value >= 1 && value <= 9;
    }

    private void addNumber() {
        String input = JOptionPane.showInputDialog(this,
                "Digite o número e sua posição no formato: linha,coluna,valor\nEx: 1,2,5");

        if (input != null && !input.trim().isEmpty()) {
            try {
                String[] values = input.split(",");
                int row = Integer.parseInt(values[0]);
                int col = Integer.parseInt(values[1]);
                int value = Integer.parseInt(values[2]);

                if (!isValidPosition(row, col) || !isValidValue(value)) {
                    JOptionPane.showMessageDialog(this, "Posição ou valor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (fixedCells[row][col]) {
                    JOptionPane.showMessageDialog(this, "Número fixo, não pode ser alterado", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                board[row][col] = value;
                updateBoardDisplay();

                if (hasConflicts(row, col)) {
                    cellFields[row][col].setBackground(new Color(255, 200, 200));
                    JOptionPane.showMessageDialog(this, "Este número causa conflitos no tabuleiro", "Aviso", JOptionPane.WARNING_MESSAGE);
                } else {
                    cellFields[row][col].setBackground(Color.WHITE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean hasConflicts(int row, int col) {
        int value = board[row][col];
        if (value == 0) return false;

        // Linha
        for (int c = 0; c < SIZE; c++) {
            if (c != col && board[row][c] == value) return true;
        }
        // Coluna
        for (int r = 0; r < SIZE; r++) {
            if (r != row && board[r][col] == value) return true;
        }
        // Subgrid 3x3
        int startRow = row - row % SUBGRID_SIZE;
        int startCol = col - col % SUBGRID_SIZE;
        for (int r = startRow; r < startRow + SUBGRID_SIZE; r++) {
            for (int c = startCol; c < startCol + SUBGRID_SIZE; c++) {
                if ((r != row || c != col) && board[r][c] == value) return true;
            }
        }
        return false;
    }

    private void removeNumber() {
        String input = JOptionPane.showInputDialog(this,
                "Digite a posição para remover no formato: linha,coluna\nEx: 1,2");

        if (input != null && !input.trim().isEmpty()) {
            try {
                String[] values = input.split(",");
                int row = Integer.parseInt(values[0]);
                int col = Integer.parseInt(values[1]);

                if (!isValidPosition(row, col)) {
                    JOptionPane.showMessageDialog(this, "Posição inválida", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (fixedCells[row][col]) {
                    JOptionPane.showMessageDialog(this, "Número fixo, não pode ser removido", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                board[row][col] = 0;
                updateBoardDisplay();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Formato inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void checkGame() {
        boolean hasConflicts = false;
        boolean hasEmpty = false;

        // Reset cores base
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (fixedCells[r][c]) {
                    cellFields[r][c].setBackground(new Color(240, 240, 240));
                } else {
                    cellFields[r][c].setBackground(Color.WHITE);
                }
            }
        }

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == 0) {
                    hasEmpty = true;
                } else if (hasConflicts(r, c)) {
                    hasConflicts = true;
                    cellFields[r][c].setBackground(new Color(255, 200, 200));
                }
            }
        }

        if (!hasEmpty && !hasConflicts) {
            JOptionPane.showMessageDialog(this, "Parabéns! Sudoku correto e completo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else if (hasConflicts) {
            JOptionPane.showMessageDialog(this, "Conflitos detectados (células vermelhas).", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else if (hasEmpty) {
            JOptionPane.showMessageDialog(this, "Tabuleiro incompleto mas sem conflitos.", "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showGameStatus() {
        boolean started = false;
        boolean hasConflicts = false;
        boolean hasEmpty = false;

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] != 0) {
                    started = true;
                    if (hasConflicts(r, c)) {
                        hasConflicts = true;
                    }
                } else {
                    hasEmpty = true;
                }
            }
        }

        String status;
        if (!started) status = "Não iniciado";
        else if (hasEmpty) status = "Incompleto";
        else status = "Completo";

        String errors = hasConflicts ? "com erros" : "sem erros";

        JOptionPane.showMessageDialog(this, "Status do jogo: " + status + " " + errors, "Status do Jogo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (!fixedCells[r][c]) {
                    board[r][c] = 0;
                }
            }
        }
        updateBoardDisplay();
    }

    private void finishGame() {
        boolean hasEmpty = false;
        boolean hasConflicts = false;

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == 0) hasEmpty = true;
                else if (hasConflicts(r, c)) hasConflicts = true;
            }
        }

        if (hasEmpty) {
            JOptionPane.showMessageDialog(this, "Não pode finalizar: há células vazias.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (hasConflicts) {
            JOptionPane.showMessageDialog(this, "Não pode finalizar: existem conflitos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Parabéns! Sudoku completo e correto. Finalizando...", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void updateBoardDisplay() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] != 0) {
                    cellFields[r][c].setText(String.valueOf(board[r][c]));
                    if (fixedCells[r][c]) {
                        cellFields[r][c].setEditable(false);
                        cellFields[r][c].setBackground(new Color(240, 240, 240));
                    } else {
                        cellFields[r][c].setEditable(true);
                        cellFields[r][c].setBackground(Color.WHITE);
                    }
                } else {
                    cellFields[r][c].setText("");
                    cellFields[r][c].setEditable(true);
                    cellFields[r][c].setBackground(Color.WHITE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuGame game = new SudokuGame();
            game.setVisible(true);
        });
    }
}
